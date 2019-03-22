package protocol.chat.subprotocol;

import game.user.User;
import protocol.chat.ChatProtocol;

public class SystemSubChatProtocol extends ChatProtocol {
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
