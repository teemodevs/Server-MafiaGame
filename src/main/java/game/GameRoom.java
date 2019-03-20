package game;

import protocol.Protocol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameRoom {
    private static GameRoom gameRoom = new GameRoom();

    private Map<String, User> connectedUserMap;

    private GameRoom() {
        connectedUserMap = new HashMap<String, User>();
    }

    public static GameRoom getInstance() {
        return gameRoom;
    }

    public void addUser(User user) {
        connectedUserMap.put(user.getUserId(), user);
    }

    public void deleteUser(User user) {
        connectedUserMap.remove(user.getUserId());
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
