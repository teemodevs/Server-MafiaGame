package game;

import java.util.HashMap;
import java.util.Map;

/**
 * GameRoom을 관리하는 클래스 
 */
public class GameRoomManager {
	private static GameRoomManager gameRoomManager = new GameRoomManager();
	
	private Map<Integer, GameRoom> gameRoomMap;
	
	private GameRoomManager() {
		gameRoomMap = new HashMap<>();
	}
	
	public static GameRoomManager getInstance() {
		return gameRoomManager;
	}
	
	/**
	 * 방 만들기
	 */
	public GameRoom makeGameRoom(String gameRoomName) {
		
		GameRoom newGameRoom = null;
		
		for (int index = 0; index <= this.gameRoomMap.size(); index++) {
			if (!this.gameRoomMap.containsKey(index)) {
				newGameRoom = new GameRoom(index, gameRoomName);
				this.gameRoomMap.put(index, newGameRoom);
				break;
			}
		}
		
		return newGameRoom;
	}
	
	/**
	 * 방 삭제 (마지막 유저 퇴장 시)
	 */
	public void deleteGameRoom(GameRoom gameRoom) {
		this.gameRoomMap.remove(gameRoom.getGameRoomNumber());
	}
	
	/**
	 * 유저가 참여할 수 있는 방 맵 반환
	 */
	public Map<Integer, String> getJoinableGameRoomMap() {
		Map<Integer, String> joinableGameRoomMap = new HashMap<>();
		
		for(int index : this.gameRoomMap.keySet()) {
			GameRoom gameRoom = this.gameRoomMap.get(index);
			if (!gameRoom.isPlaying())
				joinableGameRoomMap.put(gameRoom.getGameRoomNumber(), gameRoom.getGameRoomName());
		}
		
		return joinableGameRoomMap;
	}
	
	/**
	 * 참가할 방에 대한 정보를 리턴
	 */
	public GameRoom getGameRoom(int gameRoomNumber) {
		return this.gameRoomMap.get(gameRoomNumber);
	}
}
