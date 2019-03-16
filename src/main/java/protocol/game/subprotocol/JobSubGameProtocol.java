package protocol.game.subprotocol;

import game.User;
import protocol.game.GameProtocol;

public class JobSubGameProtocol extends GameProtocol {
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
