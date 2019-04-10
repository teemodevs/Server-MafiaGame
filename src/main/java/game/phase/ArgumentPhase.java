package game.phase;

import game.PhaseTimer;
import game.user.User;

/**
 * 최후의 변론 Phase
 */
public class ArgumentPhase implements Phase {
    private static final int PHASE_INTERVAL_SECONDS = 5;
    private static ArgumentPhase argumentPhase = new ArgumentPhase();

    private ArgumentPhase() {}

    public static ArgumentPhase getInstance() {
        return argumentPhase;
    }

    @Override
    public int getInterval() {
        return PHASE_INTERVAL_SECONDS;
    }

    @Override
    public void executeResult(PhaseTimer phaseTimer) {
        System.out.println(this.getClass().getSimpleName() + ". executeResult()");
        phaseTimer.setPhase(ExecuteVotePhase.getInstance());
    }

    /**
     * 최후의 변론 중에는 유저를 선택해도 별다른 작업이 없음
     */
    @Override
    public void selectUserExecute(User user, String targetUserId) {
        System.out.println(this.getClass().getSimpleName() + ".selectUserExecute() " + user.getUserId() + ", " + targetUserId);
    }
}
