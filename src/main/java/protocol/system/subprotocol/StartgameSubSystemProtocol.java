package protocol.system.subprotocol;

import game.User;
import protocol.system.SystemProtocol;

public class StartgameSubSystemProtocol extends SystemProtocol {
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
