package protocol.system.subprotocol;

import java.util.List;

import game.GameRoomManager;
import game.user.User;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 현재 게임방에 접속 유저의 리스트를 반환
 * 클라 to 서버 : 현재 게임방에 접속 유저의 리스트를 요청
 */
public class GameRoomUserListSubSystemProtocol extends SystemProtocol {
	private List<Integer> userIdList;
	
	public List<Integer> getUserIdList() {
		return userIdList;
	}


	public void setUserIdList(List<Integer> userIdList) {
		this.userIdList = userIdList;
	}
	
	/**
	 * 게임방에 접속해있는 모든 유저 (본인 포함)의 리스트를 반환
	 */
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        this.userIdList = GameRoomManager.getInstance().getJoinableGameRoomNumberList();
        user.sendProtocol(this);
    }
}
