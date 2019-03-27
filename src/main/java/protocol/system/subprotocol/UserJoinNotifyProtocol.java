package protocol.system.subprotocol;

import game.user.User;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 방의 모든 유저에게 방에 입장했다고 알림
 * 클라 to 서버 : -
 */
public class UserJoinNotifyProtocol extends SystemProtocol {
	private String userId; // 요청한 유저의 id
	
	public String getUserId() {
		return userId;
	}

	public UserJoinNotifyProtocol setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	/**
	 * 클라이언트에서 전송한 게임방 번호를 기반으로 찾아서 들어갈 수 있는 상황이면  들어가게 처리한다음 결과를 클라이언트에 통보
	 * 게임방이 없는 경우, 플레이중인 경우, 풀방인 경우 입장 불가
	 */
	@Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
