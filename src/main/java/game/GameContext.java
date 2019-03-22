package game;

public class GameContext {
	private boolean isPlaying; // 현재 게임중 여부

	public boolean isPlaying() {
		return isPlaying;
	}

	public GameContext setPlaying(boolean isPlaying) {
		System.out.println(this.getClass().getSimpleName() + ".setPlaying : " + isPlaying);
		this.isPlaying = isPlaying;
		return this;
	}
	
}
