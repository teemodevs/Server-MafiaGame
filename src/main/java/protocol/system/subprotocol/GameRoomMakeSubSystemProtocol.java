package protocol.system.subprotocol;

import game.GameRoom;
import game.GameRoomManager;
import game.user.User;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 유저에 의해 만들어진 방에 대한 번호 정보를 리턴
 * 클라 to 서버 : 서버에 새로운 방 만들기 요청
 */
public class GameRoomMakeSubSystemProtocol extends SystemProtocol {
	
	private int newGameRoomNumber; // 만들어진 방 번호
	
    public int getNewGameRoomNumber() {
		return newGameRoomNumber;
	}

	public void setNewGameRoomNumber(int newGameRoomNumber) {
		this.newGameRoomNumber = newGameRoomNumber;
	}

	/**
	 * 방을 만든 후, 만들어진 방 번호를 클라이언트에게 전송
	 */
	@Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        GameRoom newGameRoom = GameRoomManager.getInstance().makeGameRoom();
        this.newGameRoomNumber = newGameRoom.getGameRoomNumber();
        user.sendProtocol(this);
    }
}
