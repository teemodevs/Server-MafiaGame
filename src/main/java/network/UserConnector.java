package network;

import game.User;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UserConnector implements Runnable {
    // 서버 접속 포트
    private final static int PORT = 30000;
    private ServerSocket serverSocket;

    public UserConnector() {
    }

    /**
     * Starting accept users
     */
    public void run() {
        try {
        	serverSocket = new ServerSocket(PORT);
            Socket socket = null;
            while ((socket = serverSocket.accept()) != null) {
                new User(socket).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

}