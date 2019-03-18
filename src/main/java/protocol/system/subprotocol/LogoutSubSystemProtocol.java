package protocol.system.subprotocol;

import game.GameRoom;
import game.User;
import protocol.system.SystemProtocol;

public class LogoutSubSystemProtocol extends SystemProtocol {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public LogoutSubSystemProtocol setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        GameRoom.getInstance().sendProtocolToAllUsers(this);
        user.logout();
    }
}
