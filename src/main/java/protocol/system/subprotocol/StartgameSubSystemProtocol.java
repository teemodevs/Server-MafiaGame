package protocol.system.subprotocol;

import game.GameRoom;
import game.user.User;
import protocol.Protocol;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 모든 유저에게 게임이 시작됐다고 알림
 * 클라 to 서버 : 게임시작요청(RoomMaster만 요청 가능)
 **/
public class StartgameSubSystemProtocol extends SystemProtocol {
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
			Protocol protocol = new StartgameFailedSubSystemProtocol().setReason("최소 3명 이상부터 게임시작 가능");
			masterUser.sendProtocol(protocol);
		}
	}
}
