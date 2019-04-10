package game.user;

import game.job.Job;

/**
 * 특정 유저의 게임 상태를 저장
 */
public class UserGameState {
	private Job 		job; 		 // 유저 직업
	private boolean 	alive;  	 // 현재 살아있는지 죽어있는지 여부
	private VoteContext voteContext; // 투표 관련 정보 저장
	private FlagContext flagContext; // 직업 지정 정보 저장

	UserGameState() {
		this.voteContext = new VoteContext();
		this.flagContext = new FlagContext();
	}

	/**
	 * 모든 상태 초기화
	 */
	public void init() {
		this.voteContext.init();
		this.flagContext.init();
		this.setAlive(true);
	}
	
	/**
	 * User의 직업 객체 반환
	 * @return job Job User의 직업
	 */
	Job getJob() {
		return job;
	}

	/**
	 * User의 직업 객체 할당
	 * @param job Job 유저에게 할당할 Job 객체
	 */
	UserGameState setJob(Job job) {
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
	UserGameState setAlive(boolean alive) {
		this.alive = alive;
		return this;
	}

	public VoteContext getVoteContext() {
		return this.voteContext;
	}

	public FlagContext getFlagContext() {
		return this.flagContext;
	}
	

}
