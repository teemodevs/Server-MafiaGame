package protocol.chat.subprotocol;

import game.user.User;
import protocol.chat.ChatProtocol;

/**
 * 서버 to 클라 : 유저에게 시스템 채팅을 전달
 * 클라 to 서버 : -
 **/
public class SystemSubChatProtocol extends ChatProtocol {
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
