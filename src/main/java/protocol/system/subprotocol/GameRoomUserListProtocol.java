package protocol.system.subprotocol;

import java.util.List;

import game.GameRoom;
import game.GameRoomManager;
import game.user.User;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 현재 게임방에 접속 유저의 리스트를 반환
 * 클라 to 서버 : 현재 게임방에 접속 유저의 리스트를 요청
 */
public class GameRoomUserListProtocol extends SystemProtocol {
	private List<String> userIdList;
	
	public List<String> getUserIdList() {
		return userIdList;
	}

	public void setUserIdList(List<String> userIdList) {
		this.userIdList = userIdList;
	}
	
	/**
	 * 게임방에 접속해있는 모든 유저 (본인 포함)의 리스트를 반환
	 */
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        GameRoom gameRoom = user.getGameRoom();

        this.userIdList = gameRoom.getLoginUserStringList();
        user.sendProtocol(this);
    }
}
