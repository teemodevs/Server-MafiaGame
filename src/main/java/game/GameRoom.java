package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import game.user.User;
import protocol.Protocol;
import protocol.system.subprotocol.RoomMasterSubSystemProtocol;

public class GameRoom {
	// TODO : 현재는 싱글톤으로 되어있어 서버 하나에 게임방이 1개이지만, 이후 여러 개의 방을 생성할 수 있도록 변경
    private static GameRoom gameRoom = new GameRoom();
    private User roomMaster; // 방장 User
    private Map<String, User> connectedUserMap; // 접속한 모든 유저의 Map
    private GameContext gameContext; // 게임 정보 및 로직 관련
    
    private GameRoom() {
        connectedUserMap = new HashMap<String, User>();
    }

    public static GameRoom getInstance() {
        return gameRoom;
    }

    /**
     * 현재 GameRoom에 접속된 유저 수를 리턴
     **/
    public int getPlayerCount() {
    	return connectedUserMap.size();
    }
    
    /**
     * 현재 GameRoom의 방장 User 객체 리턴
     **/
    public User getMasterUser() {
    	return this.roomMaster;
    }
    
    /**
     * 유저를 해당 GameRoom에 추가, 첫 유저일 경우 방장으로 지정
     **/
    public void addUser(User user) {
    	connectedUserMap.put(user.getUserId(), user);
    	
    	// 첫 유저인 경우 방장으로 선정
    	if (connectedUserMap.size() == 1) {
    		this.roomMaster = user;
    		Protocol protocol = new RoomMasterSubSystemProtocol()
    								.setMasterId(user.getUserId());
    		this.roomMaster.sendProtocol(protocol);
    	}
    	
    }

    /**
     * 유저를 해당 GameRoom에서 삭제, 방장이 나간 경우 방장 재설정
     **/
    public void deleteUser(User user) {
    	this.connectedUserMap.remove(user.getUserId());
    	
    	// 마지막 유저가 나간 경우는 roomMaster 객체를 null로 설정
    	if (this.connectedUserMap.size() == 0) {
    		this.roomMaster = null;
    		return;
    	}
    	
    	// 방장이 나간 경우 아무나 방장 선정
    	if (this.roomMaster.getUserId().equals(user.getUserId())) {
    		Random random = new Random();
    		List<String> userMapKeyList = new ArrayList<>(this.connectedUserMap.keySet());
    		String randomUserKey = userMapKeyList.get(random.nextInt(userMapKeyList.size()));
    		
    		this.roomMaster = this.connectedUserMap.get(randomUserKey);
    		
    		Protocol protocol = new RoomMasterSubSystemProtocol()
					.setMasterId(user.getUserId());
    		this.roomMaster.sendProtocol(protocol);
    	}
        
    }

    /**
     * 모든 유저에게 Protocol을 전송
     **/
    public void sendProtocol(Protocol protocol) {
        for( String userId : connectedUserMap.keySet() )
            connectedUserMap.get(userId).sendProtocol(protocol);
    }

	/**
	 * 현재 GameRoom에서 로그인한 유저 리스트를 반환
	 * */
	public List<User> getLoginUserList() {
		return new ArrayList<>(this.connectedUserMap.values());
	}

    /**
     * 현재 GameRoom에서 로그인한 유저 id 리스트를 반환
     * @return loginUserIdStringList List<String> 현재 로그인한 유저 id 리스트
     **/
    public List<String> getLoginUserStringList() {
    	List<User> loginUserList = getLoginUserList();
			List<String> loginUserIdStringList = new ArrayList<>();
    	
    	for (User user : loginUserList)
    		loginUserIdStringList.add(user.getUserId());

    	return loginUserIdStringList;
    }
    
    /**
     * 현재 GameRoom 게임 시작, gameContext 할당
     **/
    public void gameStart() {
    	gameContext = new GameContext(this);
    	gameContext.gameStart();
    }
}
