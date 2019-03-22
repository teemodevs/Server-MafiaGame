package game.job.civil;

import game.job.Job;

public class Doctor implements Job {

	@Override
	public void execute() {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
	}

}
