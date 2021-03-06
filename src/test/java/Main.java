import game.user.User;
import message.Message;
import message.MessageConverter;
import protocol.Protocol;
import protocol.chat.subprotocol.NormalChatProtocol;

public class Main {
    public static void main(String[] args) {
        Protocol protocol1 = new NormalChatProtocol();
        MessageConverter messageConverter = new MessageConverter();

        /** Serialization **/
        Message message = messageConverter.protocolToMessage(protocol1);
        System.out.println(message.getMessage());

        /** Deserialization **/
        Protocol concreteProtocol = messageConverter.messageToProtocol(message);
        concreteProtocol.execute(new User(null));
    }
}

