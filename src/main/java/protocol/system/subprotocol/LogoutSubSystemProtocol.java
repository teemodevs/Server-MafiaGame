package protocol.system.subprotocol;

import game.User;
import protocol.system.SystemProtocol;

public class LogoutSubSystemProtocol extends SystemProtocol {
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        user.logout();
    }
}
