package game;

import message.MessageSenderReceiver;
import protocol.Protocol;

import java.net.Socket;

public class User extends Thread {
    private String userId;

    // 메시시 송수신용
    private MessageSenderReceiver messageSenderReceiver;

    public String getUserId() {
        return userId;
    }

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public void connect(Socket socket) {
        this.messageSenderReceiver = new MessageSenderReceiver(socket);
    }

    @Override
    public void run() {
        while (true) {
            Protocol protocol = this.messageSenderReceiver.receiveMessage();
            protocol.execute(this);
        }
    }

    public void sendMessage(Protocol protocol) {
        this.messageSenderReceiver.sendMessage(protocol);
    }

}
