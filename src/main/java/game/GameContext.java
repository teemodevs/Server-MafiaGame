package game;

import game.job.Job;
import game.job.civil.Civil;
import game.job.civil.Doctor;
import game.job.civil.Police;
import game.job.mafia.Mafia;
import game.job.mafia.Spy;
import game.phase.NightPhase;
import game.user.User;
import protocol.Protocol;
import protocol.game.subprotocol.JobAllocationProtocol;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 게임 진행과 관련된 정보 및 로직을 가지는 클래스
 * GameRoom과 1:1 매치
 */
public class GameContext {
	private boolean 	isPlaying; 			// 현재 게임중 여부
	private List<Job> 	allocableJobList; 	// 유저에게 할당 가능한 직업 리스트
	private GameRoom 	gameRoom; 			// 해당 GameContext를 가지고 있는 GameRoom

	GameContext(GameRoom gameRoom) {
		this.gameRoom = gameRoom;
	}

	public GameRoom getGameRoom() {
		return this.gameRoom;
	}

	/**
	 * 게임 시작
	 */
	void gameStart() {
		this.isPlaying = true; //게임중으로 표시
		PhaseTimer phaseTimer = new PhaseTimer(); // Phase를 진행시키는 타이머 초기화
		phaseTimer.setGameContext(this);
		phaseTimer.setPhase(NightPhase.getInstance()); // 초기 Phase는 NightPhase로 설정
		this.initAllocableJobList();
		this.allocJob();
		phaseTimer.start();
	}

	/**
	 * 현재 플레이중 여부 리턴
	 * @return isPlaying boolean 플레이중 여부 
	 */
	boolean isPlaying() {
		return this.isPlaying;
	}

	/**
	 * 게임 종료 처리
	 * @param gameResult GameResult 게임 결과정보를 담은 객체 
	 */
	public void gameOver(GameResult gameResult) {
		this.isPlaying = false;
		System.out.println(gameResult.getWinTeam());
	}

	/**
	 * 유저에게 할당될 수 있는 직업 리스트를 선정해서 allocableJobList에 저장
	 */
	private void initAllocableJobList() {
		allocableJobList = new ArrayList<>(8);

		// 기본 직업 할당 (3인 이상 플레이 가능)
		allocableJobList.add(new Mafia());
		allocableJobList.add(new Police());
		allocableJobList.add(new Doctor());

		// 4명 이상인 경우 시민 추가
		if (gameRoom.getPlayerCount() >= 4)
			allocableJobList.add(new Civil());

		// 5명 이상인 경우 마피아 추가
		if (gameRoom.getPlayerCount() >= 5)
			allocableJobList.add(new Mafia());

		// 6명 이상인 경우 시민 추가
		if (gameRoom.getPlayerCount() >= 6)
			allocableJobList.add(new Civil());

		// 7명 이상인 경우 시민 추가
		if (gameRoom.getPlayerCount() >= 7)
			allocableJobList.add(new Civil());

		// 8명 이상인 경우 스파이 추가
		if (gameRoom.getPlayerCount() >= 8)
			allocableJobList.add(new Spy());

		Collections.shuffle(allocableJobList);
	}

	/**
	 * 유저에게 직업 할당
	 */
	private void allocJob() {
		List<User> loginUserList = gameRoom.getLoginUserList();
		for (User user : loginUserList) {
			Job job = this.allocableJobList.remove(0);
			user.setJob(job);
			Protocol protocol = new JobAllocationProtocol().setJobName(job.getClass().getSimpleName());
			user.sendProtocol(protocol);
			System.out.println(user.getUserId() + " : " + user.getJob().getClass().getSimpleName());
		}
	}
	
}
