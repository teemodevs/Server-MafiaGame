package game.user;

import game.GameRoom;
import game.job.Job;
import message.MessageSenderReceiver;
import protocol.Protocol;
import protocol.game.subprotocol.UserDeadProtocol;
import protocol.system.subprotocol.LogoutProtocol;

import java.net.Socket;

/**
 * 소켓 접속 유저 클래스
 * TODO : User를 Thread를 상속받지 않고 Runnable 인터페이스를 구현하도록 변경
 */
public class User extends Thread {
	private GameRoom 				gameRoom; 				// 유저가 접속한 GameRoom
    private String 					userId; 				// 유저  id
    private UserGameState 			userGameState; 			// 유저 게임 상태 저장
    private MessageSenderReceiver 	messageSenderReceiver;	// 메시시 송수신용

    public User(Socket socket) {
        this.messageSenderReceiver = new MessageSenderReceiver(socket);
        this.userGameState = new UserGameState();
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

    public Job getJob() {
        return userGameState.getJob();
    }

    public User setJob(Job job) {
        job.setUser(this);
        this.userGameState.setJob(job);
        return this;
    }
    
    // User가 Thread를 상속받는데, isAlive()가 겹쳐서 네이밍을 다르게 줌
    public boolean isUserAlive() {
    	return this.userGameState.isAlive();
    }

    /**
     * 클라이언트로부터 메시지를 계속해서 읽어들임, 읽어들인 메시지는 Protocol 타입으로 받아서  바로 실행시킴
     */
	@Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            Protocol protocol = this.messageSenderReceiver.receiveMessage();
            protocol.execute(this);
        }
    }

    /**
     * 현재 User에게 포로토콜 전송
     */
    public void sendProtocol(Protocol protocol) {
        this.messageSenderReceiver.sendMessage(protocol);
    }

    /**
     * 해당 유저를 로그아웃처리
     */
    public void logout() {
    	
    	// 참여중인 게임방이 있다면 해당 방의 모든 유저에게 로그아웃 했다고 알리고 해당 유저 제거
    	if (this.gameRoom != null) {
    		Protocol protocol = new LogoutProtocol().setUserId(this.userId);
    		this.gameRoom.sendProtocol(protocol);
    		this.gameRoom.deleteUser(this);
    	}
    	
        this.interrupt();
    }

    /**
     * 유저 죽음 처리
     */
    public void dead() {
        this.getUserGameState().setAlive(false);
        Protocol protocol = new UserDeadProtocol().setKilledUserId(this.getUserId());
        this.gameRoom.sendProtocol(protocol);
    }
}
