package game;

import message.MessageSenderReceiver;
import protocol.Protocol;

import java.net.Socket;

public class User extends Thread {
    private String userId;

    private MessageSenderReceiver messageSenderReceiver;

    public User(Socket socket) {
        this.messageSenderReceiver = new MessageSenderReceiver(socket);
    }

    public String getUserId() {
        return userId;
    }

    public User setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Protocol protocol = this.messageSenderReceiver.receiveMessage();
            protocol.execute(this);
        }
    }

    public void sendMessage(Protocol protocol) {
        this.messageSenderReceiver.sendMessage(protocol);
    }

    public void logout() {
        this.interrupt();
    }
}
