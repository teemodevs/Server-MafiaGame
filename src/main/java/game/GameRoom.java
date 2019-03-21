package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import protocol.Protocol;
import protocol.system.subprotocol.RoomMasterSubSystemProtocol;

public class GameRoom {
    private static GameRoom gameRoom = new GameRoom();
    private User roomMaster;
    private Map<String, User> connectedUserMap;

    private GameRoom() {
        connectedUserMap = new HashMap<String, User>();
    }

    public static GameRoom getInstance() {
        return gameRoom;
    }

    public void addUser(User user) {
    	connectedUserMap.put(user.getUserId(), user);
    	
    	// 첫 유저인 경우 방장으로 선정
    	if (connectedUserMap.size() == 1) {
    		this.roomMaster = user;
    		Protocol protocol = new RoomMasterSubSystemProtocol()
    								.setMasterId(user.getUserId());
    		this.roomMaster.sendMessage(protocol);
    	}
    	
    }

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
    		this.roomMaster.sendMessage(protocol);
    	}
        
    }

    public void sendProtocolToAllUsers(Protocol protocol) {
        for( String userId : connectedUserMap.keySet() )
            connectedUserMap.get(userId).sendMessage(protocol);
    }
    
    public List<String> getLoginUserList() {
    	List<User> loginUserList = new ArrayList<>(this.connectedUserMap.values());
    	List<String> loginUserIdStringList = new ArrayList<>();
    	
    	for (User user : loginUserList)
    		loginUserIdStringList.add(user.getUserId());
    	return loginUserIdStringList;
    }
}
