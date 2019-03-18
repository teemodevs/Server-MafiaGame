package protocol.chat.subprotocol;

import game.GameRoom;
import game.User;
import protocol.chat.ChatProtocol;

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
        GameRoom.getInstance().chatToAllUser(this);
    }
}
