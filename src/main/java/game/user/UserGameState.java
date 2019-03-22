package game.user;

import game.job.Job;

public class UserGameState {
	private Job job; 		// 유저 직업
	private boolean alive;  // 현재 살아있는지 죽어있는지 여부
	
	public UserGameState() {
		this.init();
	}
	
	public void init() {
		this.setAlive(true);
	}
	
	public Job getJob() {
		return job;
	}

	public UserGameState setJob(Job job) {
		this.job = job;
		return this;
	}

	public boolean isAlive() {
		return alive;
	}

	public UserGameState setAlive(boolean alive) {
		this.alive = alive;
		return this;
	}
}
