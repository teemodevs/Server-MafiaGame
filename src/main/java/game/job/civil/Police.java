package game.job.civil;

import game.GameRoom;
import game.job.Job;
import game.user.User;
import protocol.Protocol;
import protocol.game.subprotocol.JobActionProtocol;

/**
 * 경찰직업
 */
public class Police extends CivilTeam implements Job {
	private User user;
	private boolean activate;

	public Police() {
		init();
	}

	@Override
	public void init() {
		this.activate = true;
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void execute(String userId) {
		System.out.println(this.getClass().getSimpleName() + ".execute()");

		if (!this.activate)
			return;

		this.activate = false;
		GameRoom gameRoom = this.user.getGameRoom();
		User user = gameRoom.getUserById(userId);
		Protocol protocol = new JobActionProtocol().setTarget(user.getJob().getClass().getSimpleName());
		this.user.sendProtocol(protocol);
	}

}
