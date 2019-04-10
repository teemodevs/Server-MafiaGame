package game.phase;

import game.PhaseTimer;
import game.user.User;

/**
 * Phase 추상화 인터페이스
 */
public interface Phase {
    /**
     * 현재 페이즈가 유지될 시간을 초 단위로 리턴
     * @return second 페이즈가 유지될 시간 (초 단위)
     */
    int getInterval();

    /**
     * 현재 페이즈동안 유저가 입력한 값들을 바탕으로 결과를 반영
     * @param phaseTimer PhaseTimer 다음 Phase를 세팅할 대상인 PhaseTimer
     */
    void executeResult(PhaseTimer phaseTimer);

    /**
     * 현재 페이즈에 따라 유저 선택 시 적절한 로직 수행
     * @param selectUser User 선택을 한 유저
     * @param targetUserId String 지목당한 유저의 Id
     */
    void selectUserExecute(User selectUser, String targetUserId);
}
