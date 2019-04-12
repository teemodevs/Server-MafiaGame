package game;

import game.job.Job;
import game.job.civil.Civil;
import game.job.civil.CivilTeam;
import game.job.civil.Doctor;
import game.job.civil.Police;
import game.job.mafia.Mafia;
import game.job.mafia.MafiaTeam;
import game.job.mafia.Spy;
import game.phase.NightPhase;
import game.user.User;
import protocol.Protocol;
import protocol.game.subprotocol.JobAllocationProtocol;
import protocol.system.subprotocol.EndgameProtocol;

import java.util.*;

/**
 * 게임 진행과 관련된 정보 및 로직을 가지는 클래스
 * GameRoom과 1:1 매치
 */
public class GameContext {
	private boolean 	isPlaying; 			// 현재 게임중 여부
	private List<Job> 	allocableJobList; 	// 유저에게 할당 가능한 직업 리스트
	private GameRoom 	gameRoom; 			// 해당 GameContext를 가지고 있는 GameRoom
	private PhaseTimer 	phaseTimer;			// Phase를 진행시키는 타이머
	private GameResult 	gameResult;         // 게임 결과를 저장

	GameContext(GameRoom gameRoom) {
		this.gameRoom = gameRoom;
	}

	public GameRoom getGameRoom() {
		return this.gameRoom;
	}

	public void setPhaseTimer(PhaseTimer phaseTimer) {
		this.phaseTimer = phaseTimer;
	}
	/**
	 * 게임 시작
	 */
	void gameStart() {
		this.isPlaying = true; //게임중으로 표시
		gameResult = new GameResult();
		phaseTimer = new PhaseTimer(); // Phase를 진행시키는 타이머 초기화
		phaseTimer.setGameContext(this);
		phaseTimer.setPhase(NightPhase.getInstance()); // 초기 Phase는 NightPhase로 설정
		this.initUserState();
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
	 */
	private void gameOver() {
		Map<String, String> userJobMap = gameResult.getUserJobMap();
		StringBuilder userJobMapString = new StringBuilder();

		for (String userId : userJobMap.keySet()) {
			userJobMapString.append(userId + " : " + userJobMap.get(userId) + "\n");
		}
		Protocol protocol = new EndgameProtocol().setGameResult(gameResult);
		gameRoom.sendProtocol(protocol);
		this.isPlaying = false;
	}

	/**
	 * 게임 종료 여부 확인
	 */
	public boolean checkGameOver() {
		// 생존자의 수를 구함
		List<User> survivorUserList = this.gameRoom.getGameContext().getSurvivorUserList();

		// 마피아 팀 생존자 수와 시민 팀 생존자 수
		int mafiaTeamSurvivorCount = 0;
		int civilTeamSurvivorCount = 0;

		for (User user : survivorUserList) {
			Job userJob = user.getJob();
			if (userJob instanceof CivilTeam) civilTeamSurvivorCount++;
			if (userJob instanceof MafiaTeam) mafiaTeamSurvivorCount++;
		}

		// '마피아 팀 생존자 수 >= 시민 팀 생존자 수' 마피아팀 승리
		if(mafiaTeamSurvivorCount >= civilTeamSurvivorCount) {
			gameResult.setWinTeam(MafiaTeam.class);
			this.gameOver();
			return true;
		}
		
		// '마피아 팀 생존자 수 <= 0' 인 경우 시민팀 승리
		if (mafiaTeamSurvivorCount <= 0) {
			gameResult.setWinTeam(CivilTeam.class);
			this.gameOver();
			return true;
		}

		return false;
	}

	/**
	 * 유저상태 초기화
	 */
	private void initUserState() {
		for (User user : this.gameRoom.getLoginUserList()) {
			user.getUserGameState().init();
		}
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
			gameResult.addUserJobMap(user.getUserId(), job.getClass().getSimpleName());
			Protocol protocol = new JobAllocationProtocol().setJobName(job.getClass().getSimpleName());
			user.sendProtocol(protocol);
		}
	}
	
	/**
	 * 생존한 유저의 리스트를 반환
	 * @return survivorUserList List<User> 생존한 유저의 리스트
	 * */
	public List<User> getSurvivorUserList() {
		List<User> survivorUserList = new ArrayList<>();
		
		for (User user : this.getGameRoom().getLoginUserList()) {
			if (user.isUserAlive())
				survivorUserList.add(user);
		}
		
		return survivorUserList;
	}

	/**
	 * Phase에 따라 유저 선택 시 작업을 진행
	 * @param selectUser User 다른 유저를 선택한 유저 객체
	 * @param targetUserId String 지목당한 유저의 Id
	 */
	public void selectionUser(User selectUser, String targetUserId) {
		this.phaseTimer.getPhase().selectUserExecute(selectUser, targetUserId);
	}

	/**
	 * 생존자 중 가장 많은 마피아 투표 수를 얻은 유저 반환
	 */
	public User getMostMafiaVotedUser() {
		return Collections.max(this.getSurvivorUserList(), new MaxVoteCountUserComparator());
	}

	/**
	 * 투표 받은 득표수를 기준으로 비교하는 Custom Comparator
	 */
	class MaxVoteCountUserComparator implements Comparator<User> {
		@Override
		public int compare(User o1, User o2) {
			return o1.getUserGameState().getVoteContext().getMafiaVotedCount()
					- o2.getUserGameState().getVoteContext().getMafiaVotedCount();
		}
	}
}
