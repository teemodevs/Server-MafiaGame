package game;

import java.util.HashMap;
import java.util.Map;

public class ServerContext {
    private static ServerContext serverContext = new ServerContext();

    private Map<String, User> connectedUserList;

    private ServerContext() {
        connectedUserList = new HashMap<String, User>();
    }

    public static ServerContext getInstance() {
        return serverContext;
    }

    public void addUser(User user) {
        connectedUserList.put(user.getUserId(), user);
    }
}
