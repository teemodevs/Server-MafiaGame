package network;

import exception.network.UserConnectionFailureException;
import game.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UserConnector implements Runnable {
    // 서버 접속 포트
    private final static int PORT = 30000;

    /**
     * Starting accept users
     */
    public void run() {
        while (true) {
            User user = new User();
            user.connect(this.createSocket());
            user.run();
        }
    }

    /**
     * create Socket
     *
     * @return Socket
     */
    private Socket createSocket() {
        try {
            ServerSocket serverSocket = new ServerSocket(PORT);
            return serverSocket.accept();
        } catch (IOException e) {
            throw new UserConnectionFailureException("User Connection Failed");
        }
    }

}