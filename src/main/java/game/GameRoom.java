package game;

import game.user.User;
import protocol.Protocol;
import protocol.system.subprotocol.RoomMasterProtocol;
import protocol.system.subprotocol.UserJoinNotifyProtocol;

import java.util.*;

/**
 * 게임방 클래스
 */
public class GameRoom {
	private int gameRoomNumber;
	private String gameRoomName;
	private User roomMaster; // 방장 User
    private Map<String, User> connectedUserMap; // 접속한 모든 유저의 Map
    private GameContext gameContext; // 게임 정보 및 로직 관련
    
    public GameRoom(int gameRoomNumber, String gameRoomName) {
    	gameContext = new GameContext(this);
        connectedUserMap = new HashMap<>();
        this.gameRoomNumber = gameRoomNumber;
        this.gameRoomName = gameRoomName;
    }

	public GameContext getGameContext() {
		return this.gameContext;
	}

    /**
     * 현재 GameRoom에 접속된 유저 수를 리턴
     */
    public int getPlayerCount() {
    	return connectedUserMap.size();
    }
    
    /**
     * 현재 GameRoom의 방장 User 객체 리턴
	 * @return roomMaster User 현재 GameRoom의 방장 User 객체
     */
    public User getMasterUser() {
    	return this.roomMaster;
    }
    
    /**
     * 현재 GameRoom의 방 번호 리턴
	 * @return gameRoomNumber 현재 GameRoom 방 번호
     */
    public int getGameRoomNumber() {
		return this.gameRoomNumber;
	}

	/**
	 * 현재 GameRoom의 방 제목 리턴
	 * @return gameRoomName String 현재 GameRoom 방 제목
	 */
	public String getGameRoomName() {
		return this.gameRoomName;
	}

    /**
     * 유저를 해당 GameRoom에 추가, 첫 유저일 경우 방장으로 지정
	 * @param user User GameRoom에 입장하는 유저 객체
     */
    public void addUser(User user) {
    	connectedUserMap.put(user.getUserId(), user);
    	user.setGameRoom(this);

    	this.notifyIfMaster(user);
    	this.notifyToAllUsers(user);
    	
    }

    /**
	 * 특정 방에 존재하는 모든 유저에게 특정 유저가 들어왔다는 것을 알림
	 * @param joinedUser User GameRoom에 접속한 유저 객체
	 */
    private void notifyToAllUsers(User joinedUser) {
    	for(String userId : this.connectedUserMap.keySet()) {
    		User user = this.connectedUserMap.get(userId);
    		Protocol protocol = new UserJoinNotifyProtocol()
								.setUserId(joinedUser.getUserId());
    		user.sendProtocol(protocol);
    	}
    }
    
    /**
     * 처음 입장한 유저인 경우 방장이라고 알림
	 * @param joinedUser User GameRoom에 처음 입장한 유저
     */
    private void notifyIfMaster(User joinedUser) {
    	if (connectedUserMap.size() == 1) {
    		this.roomMaster = joinedUser;
    		Protocol protocol = new RoomMasterProtocol()
    								.setMasterId(joinedUser.getUserId());
    		this.roomMaster.sendProtocol(protocol);
    	}
    }

    /**
     * 유저를 해당 GameRoom에서 삭제, 방장이 나간 경우 방장 재설정
	 * @param exitUser User GameRoom에서 나간 유저
     */
    public void deleteUser(User exitUser) {
    	this.connectedUserMap.remove(exitUser.getUserId());
    	
    	// 마지막 유저가 나간 경우는 GameRoom 삭제
    	if (this.connectedUserMap.size() == 0) {
    		GameRoomManager.getInstance().deleteGameRoom(this);
    		return;
    	}
    	
    	// 방장이 나간 경우 아무나 방장 선정
    	if (this.roomMaster.getUserId().equals(exitUser.getUserId())) {
    		Random random = new Random();
    		List<String> userMapKeyList = new ArrayList<>(this.connectedUserMap.keySet());
    		String randomUserKey = userMapKeyList.get(random.nextInt(userMapKeyList.size()));
    		
    		this.roomMaster = this.connectedUserMap.get(randomUserKey);
    		
    		Protocol protocol = new RoomMasterProtocol()
					.setMasterId(exitUser.getUserId());
    		this.roomMaster.sendProtocol(protocol);
    	}
        
    }

    /**
	 * UserId를 바탕으로 GameRoom에 접속한 User객체를 얻음
	 * @param userId String 찾을 User 객체에 대한 UserId
	 * @return user User 찾은 User 객체
	 */
    public User getUserById(String userId) {
    	return this.connectedUserMap.get(userId);
	}

    /**
     * 모든 유저에게 Protocol을 전송
	 * @param protocol Protocol 모든 유저에게 전송 할 프로토콜
     */
    public void sendProtocol(Protocol protocol) {
        for( String userId : connectedUserMap.keySet() )
            connectedUserMap.get(userId).sendProtocol(protocol);
    }

	/**
	 * 현재 GameRoom에 로그인한 유저 리스트를 반환
	 * @return userList List<User> GameRoom에 로그인한 유저 리스트
	 */
	public List<User> getLoginUserList() {
		return new ArrayList<>(this.connectedUserMap.values());
	}
    
    /**
     * 현재 GameRoom 게임 시작
     */
    public void gameStart() {
    	gameContext.gameStart();
    }
    
    /**
     * 현재 GameRoom 게임중 여부
	 * @return isPlaying boolean 현재 GameRoom 게임중 여부
     */
    public boolean isPlaying() {
    	return gameContext.isPlaying();
    }
}
