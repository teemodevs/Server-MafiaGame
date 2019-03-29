package game.phase;

import game.PhaseTimer;

/**
 * 아침 Phase
 */
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

    /**
     * 아침 Phase 결과 수행
     * 1. 아침에는 딱히 하는 게 없이 채팅만 하므로 바로 MafiaVotePhase로 넘어감
     */
    @Override
    public void executeResult(PhaseTimer phaseTimer) {
        System.out.println(this.getClass().getSimpleName() + ". executeResult()");
        phaseTimer.setPhase(MafiaVotePhase.getInstance());
    }

}
