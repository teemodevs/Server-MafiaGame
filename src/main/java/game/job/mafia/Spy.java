package game.job.mafia;

import game.job.Job;
import game.user.User;

/**
 * 스파이직업
 */
public class Spy extends MafiaTeam implements Job {
	private User user;

	@Override
	public void init() {

	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void execute(String userId) {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
	}

}
