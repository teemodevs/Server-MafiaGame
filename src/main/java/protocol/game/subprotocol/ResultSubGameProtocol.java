package protocol.game.subprotocol;

import game.user.User;
import protocol.game.GameProtocol;

public class ResultSubGameProtocol extends GameProtocol {
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
