package protocol.chat.subprotocol;

import game.GameRoom;
import game.User;
import protocol.chat.ChatProtocol;

/**
 * 서버 to 클라 : 다른 유저가 채팅한 정보를 알림 (전체 전송)
 * 클라 to 서버 : 해당 유저가 채팅을 보냄
 **/
public class NormalSubChatProtocol extends ChatProtocol {
    private String message;
    private String sender;

    public String getMessage() {
        return message;
    }

    public NormalSubChatProtocol setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public NormalSubChatProtocol setSender(String sender) {
        this.sender = sender;
        return this;
    }

    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        System.out.println("sender : " + this.sender + ", message : " + message);
        GameRoom.getInstance().sendProtocolToAllUsers(this);
    }
}
