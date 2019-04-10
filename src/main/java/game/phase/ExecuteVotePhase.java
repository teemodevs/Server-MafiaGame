package game.phase;

import game.GameContext;
import game.PhaseTimer;
import game.user.User;

import java.util.List;

/**
 * 처형 찬반 투표 Phase
 */
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

    /**
     * ExecuteVotePhase 동안 일어난 유저의 액션을 바탕으로 결과 수행
     */
    @Override
    public void executeResult(PhaseTimer phaseTimer) {
        System.out.println(this.getClass().getSimpleName() + ". executeResult()");

        GameContext gameContext = phaseTimer.getGameContext();
        List<User> survivorUserList = gameContext.getSurvivorUserList();
        // 1. 가장 투표를 많이 받은 유저를 구함
        User mostVotedUser = gameContext.getMostMafiaVotedUser();

        // 2. 1번에서 구한 유저의 executeVote 카운트를 구함
        int executeVoteCount = mostVotedUser.getUserGameState().getVoteContext().getExecuteVotedCount();

        // 3. 2번에서 구한 executeVote 카운트가 생존자의 50%가 초과하는 경우 처형 수행
        if (executeVoteCount > (survivorUserList.size() / 2))
            mostVotedUser.dead();

        // 4. 게임이 끝나지 않는다면 투표 결과 초기화 후 Night Phase 시작
        if (!gameContext.checkGameOver()) {
            for (User user : survivorUserList)
                user.getUserGameState().getVoteContext().init();
            phaseTimer.setPhase(NightPhase.getInstance());
        }
    }

    /**
     * 처형 투표 중에는 별다른 작업을 하지 않음
     */
    @Override
    public void selectUserExecute(User user, String targetUserId) {
        System.out.println(this.getClass().getSimpleName() + ".selectUserExecute() " + user.getUserId() + ", " + targetUserId);
    }

}
