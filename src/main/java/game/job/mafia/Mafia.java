package game.job.mafia;

import game.GameContext;
import game.job.Job;
import game.user.User;

/**
 * 마피아직업
 */
public class Mafia extends MafiaTeam implements Job {
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

		GameContext gameContext = this.user.getGameRoom().getGameContext();

		for (User user : gameContext.getSurvivorUserList()) {
			if (user.getUserId().equals(userId))
				user.getUserGameState().getFlagContext().setMafiaSelection(true);
			else
				user.getUserGameState().getFlagContext().setMafiaSelection(false);
		}
	}

}
