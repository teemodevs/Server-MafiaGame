package protocol.system.subprotocol;

import game.GameRoom;
import game.user.User;
import protocol.system.SystemProtocol;

import java.util.ArrayList;
import java.util.List;

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

		List<User> loginUserList = gameRoom.getLoginUserList();
		List<String> loginUserIdStringList = new ArrayList<>();

		for (User userElement : loginUserList)
			loginUserIdStringList.add(userElement.getUserId());

        this.userIdList = loginUserIdStringList;
        user.sendProtocol(this);
    }
}
