package game;

import java.util.HashMap;
import java.util.Map;

/**
 * 게임 결과 클래스 
 */
public class GameResult {
	private Class<? extends Object> winTeam; 	// 이긴 팀
	private Map<String, String> 	userJobMap;	// 유저id - 유저의 직업

	GameResult() {
		userJobMap = new HashMap<>();
	}

	GameResult setWinTeam(Class<? extends Object> winTeam) {
		this.winTeam = winTeam;
		return this;
	}

	public Class<? extends Object> getWinTeam() {
        return this.winTeam;
    }

	GameResult addUserJobMap(String userId, String jobName) {
		this.userJobMap.put(userId, jobName);
		return this;
	}

	public Map<String, String> getUserJobMap() {
		return this.userJobMap;
	}
}
