package game;

import game.phase.Phase;
import protocol.Protocol;
import protocol.game.subprotocol.PhaseSubGameProtocol;
import protocol.system.subprotocol.EndgameSubSystemProtocol;

public class PhaseTimer extends Thread {
    private Phase phase;
    private GameContext gameContext;
    private int remainPhaseTime; // 남은 Phase 시간 (초 단위)

    @Override
    public void run() {

        Protocol phaseProtocol = new PhaseSubGameProtocol()
                .setPhaseName(this.phase.getClass().getSimpleName());
        System.out.println(this.phase.getClass().getSimpleName());
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
            phaseTimer.setGameContext(this.gameContext);
            phaseTimer.setPhase(this.phase);
            phaseTimer.start();
        }

        // 게임이 끝난 경우
        else {
            Protocol endGameProtocol = new EndgameSubSystemProtocol();
            this.gameContext.getGameRoom().sendProtocol(endGameProtocol);
            System.out.println("게임 종료");
        }
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
        this.remainPhaseTime = phase.getInterval();
    }

    void setGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    public GameContext getGameContext() {
        return this.gameContext;
    }
}
