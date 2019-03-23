package game.phase;

import game.GameResult;
import game.PhaseTimer;

/**
 * 처형 찬반 투표 Phase
 **/
public class ExecuteVotePhase implements Phase {
    private static final int PHASE_INTERVAL_SECONDS = 5;
    private static ExecuteVotePhase executeVotePhase = new ExecuteVotePhase();

    private ExecuteVotePhase() {}

    public static ExecuteVotePhase getInstance() {
        return executeVotePhase;
    }

    @Override
    public int getInterval() {
        return PHASE_INTERVAL_SECONDS;
    }

    @Override
    public void executeResult(PhaseTimer phaseTimer) {
        System.out.println(this.getClass().getSimpleName() + ". executeResult()");
        phaseTimer.getGameContext().gameOver(new GameResult());
    }

}
