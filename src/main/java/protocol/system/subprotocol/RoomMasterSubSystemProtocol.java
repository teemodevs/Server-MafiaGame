package protocol.system.subprotocol;

import game.user.User;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 해당 유저에게 방장이라는 것을 알림
 * 클라 to 서버 : -
 **/
public class RoomMasterSubSystemProtocol extends SystemProtocol {
	private String masterId;
	
    public String getMasterId() {
		return masterId;
	}

	public RoomMasterSubSystemProtocol setMasterId(String masterId) {
		this.masterId = masterId;
		return this;
	}
	
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
