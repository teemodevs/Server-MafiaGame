package protocol.system.subprotocol;

import java.util.List;

import game.GameRoom;
import game.GameRoomManager;
import game.user.User;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 해당 유저에게 방에 입장했다고 알림
 * 클라 to 서버 : 해당 유저가 방에 입장하는 것을 요청
 **/
public class JoinGameRoomSubSystemProtocol extends SystemProtocol {
	private String userId;
	private int gameRoomNumber;
	private boolean isJoinSuccess;
	private List<String> loginUsers; // 이미 로그인한 유저의 리스트
	
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getGameRoomNumber() {
		return gameRoomNumber;
	}

	public void setGameRoomNumber(int gameRoomNumber) {
		this.gameRoomNumber = gameRoomNumber;
	}

	public boolean isJoinSuccess() {
		return isJoinSuccess;
	}

	public void setJoinSuccess(boolean isJoinSuccess) {
		this.isJoinSuccess = isJoinSuccess;
	}

	public List<String> getLoginUsers() {
		return loginUsers;
	}

	public void setLoginUsers(List<String> loginUsers) {
		this.loginUsers = loginUsers;
	}

	@Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        
        GameRoom gameRoom = GameRoomManager.getInstance().joinGameRoom(this.gameRoomNumber);
        user.setGameRoom(gameRoom);
        
        this.setLoginUsers(gameRoom.getLoginUserStringList());
        gameRoom.sendProtocol(this);
        
    }
}
