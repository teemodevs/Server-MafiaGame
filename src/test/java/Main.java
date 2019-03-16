import game.User;
import message.Message;
import message.MessageConverter;
import protocol.Protocol;
import protocol.chat.subprotocol.NormalSubChatProtocol;

public class Main {
    public static void main(String[] args) {
        Protocol protocol1 = new NormalSubChatProtocol();
        MessageConverter messageConverter = new MessageConverter();

        /** Serialization **/
        Message message = messageConverter.protocolToMessage(protocol1);
        System.out.println(message.getMessage());

        /** Deserialization **/
        Protocol concreteProtocol = messageConverter.messageToProtocol(message);
        concreteProtocol.execute(new User(null));
    }
}

