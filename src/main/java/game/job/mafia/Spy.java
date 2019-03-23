package game.job.mafia;

import game.job.Job;

public class Spy implements Job {

	@Override
	public void execute() {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
	}

}
