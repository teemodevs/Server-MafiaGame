package protocol.chat.subprotocol;

import game.user.User;
import protocol.chat.ChatProtocol;

/**
 * 서버 to 클라 : 다른 유저가 채팅한 정보를 알림 (전체 전송)
 * 클라 to 서버 : 해당 유저가 채팅을 보냄
 */
public class NormalChatProtocol extends ChatProtocol {
    private String message;
    private String sender;

    public String getMessage() {
        return message;
    }

    public NormalChatProtocol setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getSender() {
        return sender;
    }

    public NormalChatProtocol setSender(String sender) {
        this.sender = sender;
        return this;
    }

    /**
     * 메시지를 보낸 유저가 들어가있는 방의 모든 유저에게, 메시지를 보낸 유저가 메시지를 보냈다고 알림 
     */
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        System.out.println("sender : " + this.sender + ", message : " + message);
        user.getGameRoom().sendProtocol(this);
    }
}
