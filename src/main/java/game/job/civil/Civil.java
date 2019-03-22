package game.job.civil;

import game.job.Job;

public class Civil implements Job {

	@Override
	public void execute() {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
	}

}
