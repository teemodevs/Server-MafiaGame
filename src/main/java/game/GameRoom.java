package game;

import protocol.Protocol;

import java.util.HashMap;
import java.util.Map;

public class GameRoom {
    private static GameRoom gameRoom = new GameRoom();

    private Map<String, User> connectedUserList;

    private GameRoom() {
        connectedUserList = new HashMap<String, User>();
    }

    public static GameRoom getInstance() {
        return gameRoom;
    }

    public void addUser(User user) {
        connectedUserList.put(user.getUserId(), user);
    }

    public void sendProtocolToAllUsers(Protocol protocol) {
        for( String userId : connectedUserList.keySet() )
            connectedUserList.get(userId).sendMessage(protocol);
    }
}
