package game.job.civil;

import game.job.Job;
import game.user.User;

/**
 * 시민직업
 */
public class Civil extends CivilTeam implements Job  {

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
