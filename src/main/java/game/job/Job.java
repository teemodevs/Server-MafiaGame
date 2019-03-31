package game.job;

import game.user.User;

/**
 * 직업 인터페이스
 */
public interface Job {
	/**
	 * 직업 행동 초기화
	 * ex. 경찰의 경우 Night Phase에서 1번만 능력을 사용할 수 있으며, 다음 Night Phase를 위해 초기화 기능 제공
	 */
	void init();

	/**
	 * User가 속한 GameRoom을 구하거나, 해당 유저에게만 알려야 하는 경우를 위해 User 객체를 세팅
	 * @param user User
	 */
	void setUser(User user);

	/**
	 * 해당 직업 행동 처리
	 * @param targetUserId String 지목당한 유저의 Id
	 */
	void execute(String targetUserId);


}
