package game.phase;

import game.PhaseTimer;

/**
 * Phase 추상화 인터페이스
 */
public interface Phase {
    /**
     * 현재 페이즈가 유지될 시간을 초 단위로 리턴
     */
    int getInterval();

    /**
     * 현재 페이즈동안 유저가 입력한 값들을 바탕으로 결과를 반영
     */
    void executeResult(PhaseTimer phaseTimer);
}
