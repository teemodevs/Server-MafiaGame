package game;

import game.phase.Phase;
import protocol.Protocol;
import protocol.game.subprotocol.PhaseChangeProtocol;
import protocol.system.subprotocol.EndgameProtocol;

/**
 * Phase를 진행시키는 스레드 타이머 클래스 
 */
public class PhaseTimer extends Thread {
    private Phase 		phase;			 // 현재 진행되고 있는 Phase
    private GameContext gameContext;	 // 현재 진행중인 게임 1판에 대한 정보를 가지는 클래스
    private int 		remainPhaseTime; // 남은 Phase 시간 (초 단위)

    @Override
    public void run() {

        Protocol phaseProtocol = new PhaseChangeProtocol()
                .setPhaseName(this.phase.getClass().getSimpleName());
        this.gameContext.getGameRoom().sendProtocol(phaseProtocol);

        while (remainPhaseTime > 0) {
            try {
                sleep(1000);
                remainPhaseTime--;
                System.out.println(phase.getClass().getSimpleName() + " : " + remainPhaseTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        // Phase 시간 중 유저가 진행한 로직에 따라 결과 수행
        this.phase.executeResult(this);

        // 게임이 계속 진행되는 경우 결과에 따라 다음에 진행될 Phase를 바인딩 한 후 다시 타이머 시작
        // 다음 Phase는 executeResult가 수행되는 도중 내부적으로 결정됨 (State Pattern)
        // GameContext.isPlaying 역시 executeResult가 수행되는 도중 내부적으로 결정됨
        if (gameContext.isPlaying()) {
            PhaseTimer phaseTimer = new PhaseTimer();
            this.gameContext.setPhaseTimer(phaseTimer);
            phaseTimer.setGameContext(this.gameContext);
            phaseTimer.setPhase(this.phase);
            phaseTimer.start();
        }

    }

    public void setPhase(Phase phase) {
        this.phase = phase;
        this.remainPhaseTime = phase.getInterval();
    }

    public Phase getPhase() {
        return this.phase;
    }

    void setGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    public GameContext getGameContext() {
        return this.gameContext;
    }
}
