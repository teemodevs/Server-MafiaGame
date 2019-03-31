package game.phase;

import game.GameContext;
import game.PhaseTimer;
import game.user.User;
import protocol.Protocol;
import protocol.game.subprotocol.DoctorHealProtocol;

/**
 * 밤 Phase
 */
public class NightPhase implements Phase {
    private static final int PHASE_INTERVAL_SECONDS = 5;
    private static NightPhase nightPhase = new NightPhase();

    private NightPhase() {}

    public static NightPhase getInstance() {
        return nightPhase;
    }

    @Override
    public int getInterval() {
        return PHASE_INTERVAL_SECONDS;
    }

    /**
     * NightPhase 동안 일어난 유저의 액션을 바탕으로 결과 수행
     */
    @Override
    public void executeResult(PhaseTimer phaseTimer) {
        System.out.println(this.getClass().getSimpleName() + ". executeResult()");

        GameContext gameContext = phaseTimer.getGameContext();

        // 마피아가 죽이려고 시도한 유저, 의사가 치려하려고 시도한 유저
        User mafiaKillFlagUser = null;
        User doctorHealFlagUser = null;

        for (User user : gameContext.getSurvivorUserList()) {
            if (user.getUserGameState().getFlagContext().isMafiaSelection())
                mafiaKillFlagUser = user;
            if (user.getUserGameState().getFlagContext().isDoctorSelection())
                doctorHealFlagUser = user;
        }

        // 마피아가 지정한 사람이 있는경우
        if (mafiaKillFlagUser != null) {

            // 마피아와 의사가 동일 인물을 지정한 경우
            if (mafiaKillFlagUser == doctorHealFlagUser) {
                // 의사가 해당 유저를 살렸다는 것을 모두에게 알림
                Protocol protocol = new DoctorHealProtocol().setHealUserId(doctorHealFlagUser.getUserId());
                phaseTimer.getGameContext().getGameRoom().sendProtocol(protocol);
            }

            // 동일 인물이 아닌 경우 죽음
            else {
                mafiaKillFlagUser.dead();
            }
        }

        // 게임이 끝나지 않는다면 Morning Phase로 넘어감
        if (!gameContext.checkGameOver())
            phaseTimer.setPhase(MorningPhase.getInstance());
    }

    /**
     * 밤에는 유저별 직업에 따른 작업을 수행함
     */
    @Override
    public void selectUserExecute(User user, String targetUserId) {
        System.out.println(this.getClass().getSimpleName() + ".selectUserExecute() " + user.getUserId() + ", " + targetUserId);
        user.getJob().execute(targetUserId);
    }

}
