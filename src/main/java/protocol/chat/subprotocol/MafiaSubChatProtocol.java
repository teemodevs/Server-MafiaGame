package protocol.chat.subprotocol;

import game.User;
import protocol.chat.ChatProtocol;

public class MafiaSubChatProtocol extends ChatProtocol {
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
