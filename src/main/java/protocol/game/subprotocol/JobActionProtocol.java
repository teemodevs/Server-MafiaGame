package protocol.game.subprotocol;

import game.user.User;
import protocol.game.GameProtocol;

/**
 * Night Phase 에서 상대 유저 선택 시 직업 행동
 * 서버 to 클라 : -
 * 클라 to 서버 : 해당 유저에 대한 직업 행동을 요청
 */
public class JobActionProtocol extends GameProtocol {
	private String target;
	
    public String getTarget() {
		return target;
	}

	public JobActionProtocol setTarget(String target) {
		this.target = target;
		return this;
	}

	/**
	 * 해당 유저의 직업에 따른 작업을 수행
	 * */
	@Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        System.out.println("Sender : " + user.getUserId() + ", targetId : " + this.target);

        user.getJob().execute(this.target);
    }
}
