package protocol.system.subprotocol;

import game.GameRoom;
import game.GameRoomManager;
import game.user.User;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 특정 유저에게 방에 입장했다고 알림
 * 클라 to 서버 : 특정 유저가 방에 입장하는 것을 요청
 */
public class JoinGameRoomProtocol extends SystemProtocol {
	private String 			userId;				// 요청한 유저의 id
	private int 			gameRoomNumber;		// 유저가 요청한 방의 번호
	private String			gameRoomName;		// 유저가 요청한 방의 제목
	private boolean 		isJoinSuccess;		// 게임방 입장 성공 여부
	private String 			joinFailedReason;	// 게임방 입장 실패 시 이유
	
	public String getUserId() {
		return userId;
	}

	public JoinGameRoomProtocol setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public int getGameRoomNumber() {
		return gameRoomNumber;
	}

	public JoinGameRoomProtocol setGameRoomNumber(int gameRoomNumber) {
		this.gameRoomNumber = gameRoomNumber;
		return this;
	}

	public String getGameRoomName() {
		return gameRoomName;
	}

	public JoinGameRoomProtocol setGameRoomName(String gameRoomName) {
		this.gameRoomName = gameRoomName;
		return this;
	}

	public boolean isJoinSuccess() {
		return isJoinSuccess;
	}

	public JoinGameRoomProtocol setJoinSuccess(boolean isJoinSuccess) {
		this.isJoinSuccess = isJoinSuccess;
		return this;
	}

	public String getJoinFailedReason() {
		return joinFailedReason;
	}

	public JoinGameRoomProtocol setJoinFailedReason(String joinFailedReason) {
		this.joinFailedReason = joinFailedReason;
		return this;
	}

	/**
	 * 클라이언트에서 전송한 게임방 번호를 기반으로 찾아서 들어갈 수 있는 상황이면  들어가게 처리한다음 결과를 클라이언트에 통보
	 * 게임방이 없는 경우, 플레이중인 경우, 풀방인 경우 입장 불가
	 */
	@Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        
        GameRoom gameRoom = GameRoomManager.getInstance().getGameRoom(this.gameRoomNumber);
        this.isJoinSuccess = true;
        
        if (gameRoom == null) {
        	this.isJoinSuccess = false;
        	this.joinFailedReason = "없는 방임";
        }
        
        if (gameRoom.isPlaying()) {
        	this.isJoinSuccess = false;
        	this.joinFailedReason = "이미 플레이중임";
        }
        
        if (gameRoom.getPlayerCount() >= 8) {
        	this.isJoinSuccess = false;
        	this.joinFailedReason = "풀방임";
        } 
        
        if (this.isJoinSuccess) {
	        gameRoom.addUser(user);
	        this.userId = user.getUserId();
	        this.gameRoomName = gameRoom.getGameRoomName();
        }
        
        user.sendProtocol(this);
        
    }
}
