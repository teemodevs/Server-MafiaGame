package protocol.game.subprotocol;

import game.user.User;
import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : 처형 투표 도중, 투표가 진행된 유저에 대해 현재 투표 수를 알려줌
 * 클라 to 서버 : -
 */
public class ExecuteVoteCountProtocol extends GameProtocol {
	private String userId;
	private int executeVoteCount;

	public String getUserId() {
		return userId;
	}

	public ExecuteVoteCountProtocol setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public int getExecuteVoteCount() {
		return executeVoteCount;
	}

	public ExecuteVoteCountProtocol setExecuteVoteCount(int executeVoteCount) {
		this.executeVoteCount = executeVoteCount;
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
