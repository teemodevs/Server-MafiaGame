package game.job.civil;

import game.GameContext;
import game.job.Job;
import game.user.User;

/**
 * 의사직업
 */
public class Doctor extends CivilTeam implements Job {
	private User user;

	@Override
	public void init() {

	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * 대상 유저를 치료하도록 표시, 한 유저만 치료할 수 있음
	 */
	@Override
	public void execute(String userId) {
		System.out.println(this.getClass().getSimpleName() + ".execute()");

		GameContext gameContext = this.user.getGameRoom().getGameContext();

		for (User user : gameContext.getSurvivorUserList()) {
			if (user.getUserId().equals(userId))
				user.getUserGameState().getFlagContext().setDoctorSelection(true);
			else
				user.getUserGameState().getFlagContext().setDoctorSelection(false);
		}
	}

}
