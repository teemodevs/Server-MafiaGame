package game.phase;

import game.PhaseTimer;

/**
 * 최후의 변론 Phase
 **/
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
}
