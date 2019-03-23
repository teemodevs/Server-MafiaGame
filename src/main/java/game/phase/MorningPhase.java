package game.phase;

import game.PhaseTimer;

/**
 * 아침 Phase
 **/
public class MorningPhase implements Phase {
    private static final int PHASE_INTERVAL_SECONDS = 5;
    private static MorningPhase morningPhase = new MorningPhase();

    private MorningPhase() {}

    public static MorningPhase getInstance() {
        return morningPhase;
    }

    @Override
    public int getInterval() {
        return PHASE_INTERVAL_SECONDS;
    }

    @Override
    public void executeResult(PhaseTimer phaseTimer) {
        System.out.println(this.getClass().getSimpleName() + ". executeResult()");
        phaseTimer.setPhase(MafiaVotePhase.getInstance());
    }

}
