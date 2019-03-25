package game.user;

import game.GameRoom;
import game.job.Job;
import message.MessageSenderReceiver;
import protocol.Protocol;
import protocol.system.subprotocol.LogoutSubSystemProtocol;

import java.net.Socket;

public class User extends Thread {
	private GameRoom gameRoom; // 유저가 접속한 GameRoom
    private String userId; 	// 유저  id
    private UserGameState userGameState; // 유저 게임 상태 저장
    private Job job;
    private MessageSenderReceiver messageSenderReceiver;

    public User(Socket socket) {
        this.messageSenderReceiver = new MessageSenderReceiver(socket);
    }

    public void setGameRoom(GameRoom gameRoom) {
    	this.gameRoom = gameRoom;
    }
    
    public GameRoom getGameRoom() {
    	return this.gameRoom;
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
    	
    	// 참여중인 게임방이 있다면 해당 방의 모든 유저에게 로그아웃 했다고 알리고 해당 유저 제거
    	if (this.gameRoom != null) {
    		Protocol protocol = new LogoutSubSystemProtocol().setUserId(this.userId);
    		this.gameRoom.sendProtocol(protocol);
    		this.gameRoom.deleteUser(this);
    	}
    	
        this.interrupt();
    }
}
