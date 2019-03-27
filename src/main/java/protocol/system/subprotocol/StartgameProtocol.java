package protocol.system.subprotocol;

import game.GameRoom;
import game.user.User;
import protocol.Protocol;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 모든 유저에게 게임이 시작됐다고 알림
 * 클라 to 서버 : 게임시작요청(RoomMaster만 요청 가능)
 */
public class StartgameProtocol extends SystemProtocol {
	
	/**
	 * 인원 수를 계산해서 게임을 시작 시도, 3명 이상 게임 시작 가능, 게임 시작 불가 시 방장에게 알림
	 */
	@Override
	public void execute(User user) {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
		GameRoom gameRoom = user.getGameRoom();

		if (gameRoom.getPlayerCount() >= 3) {
			gameRoom.sendProtocol(this);
			gameRoom.gameStart();
		}

		else {
			User masterUser = gameRoom.getMasterUser();
			Protocol protocol = new StartgameFailedProtocol().setReason("최소 3명 이상부터 게임시작 가능");
			masterUser.sendProtocol(protocol);
		}
	}
}
