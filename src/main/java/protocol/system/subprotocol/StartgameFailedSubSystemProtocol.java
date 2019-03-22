package protocol.system.subprotocol;

import game.user.User;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 방장에게 게임을 시작할 수 없다고 알림
 * 클라 to 서버 : -
 **/
public class StartgameFailedSubSystemProtocol extends SystemProtocol {
	private String reason;
	
	
	public String getReason() {
		return reason;
	}


	public StartgameFailedSubSystemProtocol setReason(String reason) {
		this.reason = reason;
		return this;
	}


	@Override
	public void execute(User user) {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
	}
}
