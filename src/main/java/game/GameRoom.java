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
	private User roomMaster; // 방장 User
    private Map<String, User> connectedUserMap; // 접속한 모든 유저의 Map
    private GameContext gameContext; // 게임 정보 및 로직 관련
    
    public GameRoom(int gameRoomNumber) {
    	gameContext = new GameContext(this);
        connectedUserMap = new HashMap<>();
        this.gameRoomNumber = gameRoomNumber;
    }

    /**
     * 현재 GameRoom에 접속된 유저 수를 리턴
     */
    public int getPlayerCount() {
    	return connectedUserMap.size();
    }
    
    /**
     * 현재 GameRoom의 방장 User 객체 리턴
     */
    public User getMasterUser() {
    	return this.roomMaster;
    }
    
    /**
     * 현재 GameRoom의 방 번호 리턴
     */
    public int getGameRoomNumber() {
		return gameRoomNumber;
	}
    
    /**
     * 유저를 해당 GameRoom에 추가, 첫 유저일 경우 방장으로 지정
     */
    public void addUser(User user) {
    	connectedUserMap.put(user.getUserId(), user);
    	user.setGameRoom(this);

    	this.notifyIfMaster(user);
    	this.notifyToAllUsers(user);
    	
    }

    /**
	 * 특정 방에 존재하는 모든 유저에게 특정 유저가 들어왔다는 것을 알림
	 * */
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
     */
    private void notifyIfMaster(User user) {
    	if (connectedUserMap.size() == 1) {
    		this.roomMaster = user;
    		Protocol protocol = new RoomMasterProtocol()
    								.setMasterId(user.getUserId());
    		this.roomMaster.sendProtocol(protocol);
    	}
    }

    /**
     * 유저를 해당 GameRoom에서 삭제, 방장이 나간 경우 방장 재설정
     */
    public void deleteUser(User user) {
    	this.connectedUserMap.remove(user.getUserId());
    	
    	// 마지막 유저가 나간 경우는 GameRoom 삭제
    	if (this.connectedUserMap.size() == 0) {
    		GameRoomManager.getInstance().deleteGameRoom(this);
    		return;
    	}
    	
    	// 방장이 나간 경우 아무나 방장 선정
    	if (this.roomMaster.getUserId().equals(user.getUserId())) {
    		Random random = new Random();
    		List<String> userMapKeyList = new ArrayList<>(this.connectedUserMap.keySet());
    		String randomUserKey = userMapKeyList.get(random.nextInt(userMapKeyList.size()));
    		
    		this.roomMaster = this.connectedUserMap.get(randomUserKey);
    		
    		Protocol protocol = new RoomMasterProtocol()
					.setMasterId(user.getUserId());
    		this.roomMaster.sendProtocol(protocol);
    	}
        
    }

    /**
     * 모든 유저에게 Protocol을 전송
     */
    public void sendProtocol(Protocol protocol) {
        for( String userId : connectedUserMap.keySet() )
            connectedUserMap.get(userId).sendProtocol(protocol);
    }

	/**
	 * 현재 GameRoom에서 로그인한 유저 리스트를 반환
	 */
	public List<User> getLoginUserList() {
		return new ArrayList<>(this.connectedUserMap.values());
	}

    /**
     * 현재 GameRoom에서 로그인한 유저 id 리스트를 반환
     * @return loginUserIdStringList List<String> 현재 로그인한 유저 id 리스트
     */
    public List<String> getLoginUserStringList() {
    	List<User> loginUserList = getLoginUserList();
			List<String> loginUserIdStringList = new ArrayList<>();
    	
    	for (User user : loginUserList)
    		loginUserIdStringList.add(user.getUserId());

    	return loginUserIdStringList;
    }
    
    /**
     * 현재 GameRoom 게임 시작
     */
    public void gameStart() {
    	gameContext.gameStart();
    }
    
    /**
     * 현재 GameRoom 게임중 여부
     */
    public boolean isPlaying() {
    	return gameContext.isPlaying();
    }
}
