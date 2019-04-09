package protocol.game.subprotocol;

import game.user.User;
import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : 마피아 투표 직후, 투표가 유효한 경우 가장 많이 투표를 받은 유저에 대해 현재 투표 수를
 * 클라 to 서버 : -
 */
public class MostMafiaVotedUserProtocol extends GameProtocol {
	private String userId;
	private int mafiaVoteCount;

	public String getUserId() {
		return userId;
	}

	public MostMafiaVotedUserProtocol setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public int getMafiaVoteCount() {
		return mafiaVoteCount;
	}

	public MostMafiaVotedUserProtocol setMafiaVoteCount(int mafiaVoteCount) {
		this.mafiaVoteCount = mafiaVoteCount;
		return this;
	}

	/**
	 * 해당 유저가 선택한 경우, 현재 Phase의 상태에 따라 다른 요청을 처리
	 * */
	@Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
