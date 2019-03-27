package game.job.mafia;

import game.job.Job;

/**
 * 스파이직업
 */
public class Spy implements Job {

	@Override
	public void execute() {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
	}

}
