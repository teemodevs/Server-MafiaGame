package protocol.system.subprotocol;

import game.User;
import protocol.system.SystemProtocol;

public class ServerConnectionSubSystemProtocol extends SystemProtocol {
    private boolean isConnected;

    public boolean getIsConnected() {
        return isConnected;
    }

    public ServerConnectionSubSystemProtocol setIsConnected(boolean isConnected) {
        this.isConnected = isConnected;
        return this;
    }

    @Override
    public void execute(User user) {
        this.isConnected = true;
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
