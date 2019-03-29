package game.user;

import game.job.Job;

/**
 * 특정 유저의 게임 상태를 저장
 */
public class UserGameState {
	private Job 	job; 	// 유저 직업
	private boolean alive;  // 현재 살아있는지 죽어있는지 여부
	private int 	votedCount;  // 투표 받은 횟수
	
	public UserGameState() {
		this.init();
	}
	
	/**
	 * 투표 받은 상태 초기화
	 */
	public void initVote() {
		this.votedCount = 0;
	}
	
	/**
	 * 모든 상태 초기화
	 */
	public void init() {
		this.initVote();
		this.setAlive(true);
	}
	
	/**
	 * User의 직업 객체 반환
	 * @return job Job User의 직업
	 */
	public Job getJob() {
		return job;
	}

	/**
	 * User의 직업 객체 할당
	 * @param job Job 유저에게 할당할 Job 객체
	 */
	public UserGameState setJob(Job job) {
		this.job = job;
		return this;
	}

	/**
	 * 해당 유저 생존 여부 반환
	 * @return alive boolean 해당 유저 생존 여부
	 */
	public boolean isAlive() {
		return alive;
	}

	/**
	 * 해당 유저 생존 여부 세팅
	 * @param alive boolean 해당 유저 생존 여부
	 */
	public UserGameState setAlive(boolean alive) {
		this.alive = alive;
		return this;
	}
	
	/**
	 * 해당 유저가 받은 투표 수 반환
	 * @return votedCount int 해당 유저가 받은 투표 수
	 */
	public int getVotedCount() {
		return this.votedCount;
	}
}
