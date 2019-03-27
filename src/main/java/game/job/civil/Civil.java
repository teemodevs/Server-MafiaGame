package game.job.civil;

import game.job.Job;

/**
 * 시민직업
 */
public class Civil implements Job {

	@Override
	public void execute() {
		System.out.println(this.getClass().getSimpleName() + ".execute()");
	}

}
