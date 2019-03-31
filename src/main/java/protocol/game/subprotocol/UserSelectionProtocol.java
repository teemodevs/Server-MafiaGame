package protocol.game.subprotocol;

import game.GameContext;
import game.GameRoom;
import game.user.User;
import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : -
 * 클라 to 서버 : 해당 유저를 선택
 */
public class UserSelectionProtocol extends GameProtocol {
	private String target;

    public String getTarget() {
		return target;
	}

	public UserSelectionProtocol setTarget(String target) {
		this.target = target;
		return this;
	}

	/**
	 * 해당 유저가 선택한 경우, 현재 Phase의 상태에 따라 다른 요청을 처리
	 * */
	@Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
		System.out.println("Sender : " + user.getUserId() + ", targetId : " + this.target);

		GameRoom gameRoom = user.getGameRoom();

		// 플레이중이 아니면 아무것도 하지 않음
		if(!gameRoom.isPlaying())
			return;

		// Phase에 따라 적절한 액션 수행
		GameContext gameContext= gameRoom.getGameContext();
		gameContext.selectionUser(user, this.target);
    }
}
