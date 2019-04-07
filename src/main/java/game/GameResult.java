package game;

/**
 * 게임 결과 클래스 
 */
public class GameResult {
	private Class<? extends Object> winTeam;
	
	public GameResult(Class<? extends Object> winTeam) {
		this.winTeam = winTeam;
	}
	
    public String getWinTeam() {
        return this.winTeam.getClass().getSimpleName();
    }
}
