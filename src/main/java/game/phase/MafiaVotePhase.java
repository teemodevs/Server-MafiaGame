package game.phase;

import game.GameContext;
import game.PhaseTimer;
import game.user.User;
import game.user.VoteContext;
import protocol.Protocol;
import protocol.game.subprotocol.MafiaVoteCountProtocol;

import java.util.List;

/**
 * 마피아 투표 Phase
 */
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

    /**
     * 마피아 투표 Phase 결과 수행
     *  1) 마피아 투표가 무효인 경우
     *    - 투표한 인원의 수가 생존자 수의 50% 이하이거나, 동률일 경우
     *    - Night Phase로 이동
     *     
     *  2) 마피아 투표가 유효인 경우
     *    - 투표한 인원의 수가 생존자 수의 50% 초과이면서 동률이 아닌 경우
     *    - 가장 많은 투표를 얻은 사람에 대해 Argument Phase로 이동
     */
    @Override
    public void executeResult(PhaseTimer phaseTimer) {
        System.out.println(this.getClass().getSimpleName() + ". executeResult()");
        
        GameContext gameContext = phaseTimer.getGameContext();
        
        // 투표 무효인 경우 Night Phase로 세팅
        if (!this.verifyMafiaVote(gameContext))
            phaseTimer.setPhase(NightPhase.getInstance());
        
        // 투표 유효인 경우 Argument Phase로 세팅
        else
            phaseTimer.setPhase(ArgumentPhase.getInstance());

    }

    /**
     * 마피아 투표 중에는 유저를 선택한 경우, 해당 유저의 마피아 투표 횟수를 증가시킴 (MafiaVotePhase 당 1회 가능)
     */
    @Override
    public void selectUserExecute(User user, String targetUserId) {
        System.out.println(this.getClass().getSimpleName() + ".selectUserExecute() " + user.getUserId() + ", " + targetUserId);

        VoteContext voteUserVoteContext = user.getUserGameState().getVoteContext();

        // 투표하는 유저의 마피아 투표가 가능한 경우
        if( !voteUserVoteContext.isMafiaVoteActivate() )
            return;

        // 지정된 유저를 구함
        User targetUser = user.getGameRoom().getUserById(targetUserId);

        // 지정된 유저의 마피아 투표 진행
        targetUser.getUserGameState().getVoteContext().mafiaVote();
        Protocol protocol = new MafiaVoteCountProtocol()
                                .setUserId(targetUser.getUserId())
                                .setMafiaVoteCount(targetUser.getUserGameState().getVoteContext().getMafiaVotedCount());
        user.getGameRoom().sendProtocol(protocol);

        // 마피아 투표 당 1회만 가능하므로, 투표하는 유저의 마피아 투표를 더 이상 불가능하게 함
        voteUserVoteContext.setMafiaVoteActivate(false);

    }

    /**
     * 마피아 투표가 유효인지 무효인지 판단
     *  - 생존자 중 과반수가 투표하지 않은 경우 : 무효
     *  - 동률인 경우 : 무효
     * @param gameContext GameContext 마피아 투표 유/무효 판단을 위한 게임 정보를 제공하는 GameContext
     */
    private boolean verifyMafiaVote(GameContext gameContext) {
        // 과반수 투표 여부 결정 && 동률 투표 여부 결정
        return verifyMajorityVote(gameContext) && verifySameVote(gameContext);
    }
    
    /**
     * 생존자 중 과반수 투표 여부 확인
     */
    private boolean verifyMajorityVote(GameContext gameContext) {
        List<User> survivorUserList = gameContext.getSurvivorUserList();
    	// 생존자 유저 수의 50%
    	int halfOfSurvivor = ( survivorUserList.size() / 2 );
    	
    	// 총 투표 수
    	int totalVotedCount = 0;
    	for (User user : survivorUserList)
    		totalVotedCount += user.getUserGameState().getVoteContext().getMafiaVotedCount();
    	
    	// 총 투표수 > 생존인원의 50% 인 경우 유효 (50%로 동일한 경우 무효)
        return totalVotedCount > halfOfSurvivor;
    }
    
    /**
     * 동률 여부 확인 (가장 많은 투표를 얻은 유저가 여러 명인지 확인)
     */
    private boolean verifySameVote(GameContext gameContext) {
        // 생존자 중 가장 많은 득표수를 얻은 유저를 구함
        User maxVotedUser = gameContext.getMostMafiaVotedUser();

        // 가장 많은 득표수를 구함
        int maxVoted = maxVotedUser.getUserGameState().getVoteContext().getMafiaVotedCount();

        // 가장 많은 득표수를 가진 유저의 수를 구함
        int maxVotedUserCount = 0;
        for (User user : gameContext.getSurvivorUserList()) {
            if (user.getUserGameState().getVoteContext().getMafiaVotedCount() == maxVoted)
                maxVotedUserCount++;
        }

        // 가장 많은 득표수를 가진 유저가 1명인지 (동률이 아닌지)
    	return maxVotedUserCount == 1;
    }

}
