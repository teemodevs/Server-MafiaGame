package game.user;

import game.job.Job;
import message.MessageSenderReceiver;
import protocol.Protocol;

import java.net.Socket;

import game.GameRoom;

public class User extends Thread {
    private String userId; 	// 유저  id
    private UserGameState userGameState; // 유저 게임 상태 저장
    private Job job;
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
	
    public UserGameState getUserGameState() {
		return userGameState;
	}

	public User setUserGameState(UserGameState userGameState) {
		this.userGameState = userGameState;
		return this;
	}

    public Job getJob() {
        return job;
    }

    public User setJob(Job job) {
        this.job = job;
        return this;
    }
	@Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Protocol protocol = this.messageSenderReceiver.receiveMessage();
            protocol.execute(this);
        }
    }

    /**
     * 현재 User에게 포로토콜 전송
     **/
    public void sendProtocol(Protocol protocol) {
        this.messageSenderReceiver.sendMessage(protocol);
    }

    /**
     * 해당 유저를 로그아웃처리
     **/
    public void logout() {
        GameRoom.getInstance().deleteUser(this);
        this.interrupt();
    }
}
