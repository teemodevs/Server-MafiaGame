package game.job.civil;

import game.job.Job;

/**
 * 경찰직업
 */
public class Police implements Job {

	@Override
	public void execute() {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
	}

}
