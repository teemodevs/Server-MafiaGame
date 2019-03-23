package game.phase;

import game.PhaseTimer;

/**
 * 마피아 투표 Phase
 **/
public class MafiaVotePhase implements Phase {
    private static final int PHASE_INTERVAL_SECONDS = 5;
    private static MafiaVotePhase mafiaVotePhase = new MafiaVotePhase();

    private MafiaVotePhase() {}

    public static MafiaVotePhase getInstance() {
        return mafiaVotePhase;
    }

    @Override
    public int getInterval() {
        return PHASE_INTERVAL_SECONDS;
    }

    @Override
    public void executeResult(PhaseTimer phaseTimer) {
        System.out.println(this.getClass().getSimpleName() + ". executeResult()");
        phaseTimer.setPhase(ArgumentPhase.getInstance());
    }
}
