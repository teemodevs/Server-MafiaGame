package game.job.civil;

import game.job.Job;

/**
 * 의사직업
 */
public class Doctor implements Job {

	@Override
	public void execute() {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
	}

}
