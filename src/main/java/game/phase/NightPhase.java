package game.phase;

import game.PhaseTimer;

/**
 * 밤 Phase
 **/
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

    @Override
    public void executeResult(PhaseTimer phaseTimer) {
        System.out.println(this.getClass().getSimpleName() + ". executeResult()");
        phaseTimer.setPhase(MorningPhase.getInstance());
    }

}
