package game.phase;

import java.util.List;

import game.GameContext;
import game.PhaseTimer;
import game.user.User;

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
        
        boolean voteVerifyResult = this.verifyMafiaVote(gameContext);
        
        // 투표 무효인 경우 Night Phase로 이동
        if (!voteVerifyResult)
        	phaseTimer.setPhase(NightPhase.getInstance());
        
        // 투표 유효인 경우 Argument Phase로 이동
        else 
        	phaseTimer.setPhase(ArgumentPhase.getInstance());
    }
    
    /**
     * 마피아 투표가 유효인지 무효인지 판단
     *  - 생존자 중 과반수가 투표하지 않은 경우 : 무효
     *  - 동률인 경우 : 무효
     * @param gameContext GameContext 마피아 투표 유/무효 판단을 위한 게임 정보를 제공하는 GameContext
     */
    private boolean verifyMafiaVote(GameContext gameContext) {
    	// 과반수 투표 여부 결정
    	if (!verifyMajorityVote(gameContext))
    		return false;
    
    	// 동률 투표 여부 결정
    	if (!verifySameVote(gameContext))
    		return false;
    	
    	return true;
    }
    
    /**
     * 생존자 중 과반수 투표 여부 확인
     */
    private boolean verifyMajorityVote(GameContext gameContext) {
    	// 생존자 유저 리스트
    	List<User> survivorUserList = gameContext.getSurvivorUserList();
    	
    	// 생존자 유저 수의 50%
    	int halfCount = ( survivorUserList.size() / 2 );
    	
    	// 총 투표 수
    	int totalVotedCount = 0;
    	for (User user : survivorUserList)
    		totalVotedCount += user.getUserGameState().getVotedCount();
    	
    	// 총 투표수 > 생존인원의 50% 인 경우 유효 (50%로 동일한 경우 무효)
    	if (totalVotedCount > halfCount)
    		return true;
    	else
    		return false;
    }
    
    /**
     * 동률 여부 확인 (가장 많은 투표를 얻은 유저가 여러 명인지 확인)
     */
    private boolean verifySameVote(GameContext gameContext) {
    	return false;
    }
}
