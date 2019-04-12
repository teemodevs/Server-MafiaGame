package protocol.system.subprotocol;

import game.GameResult;
import game.user.User;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 모든 유저에게 게임이 끝났다고 알림
 * 클라 to 서버 : -
 */
public class EndgameProtocol extends SystemProtocol {
    private GameResult gameResult;

    public EndgameProtocol setGameResult(GameResult gameResult) {
        this.gameResult = gameResult;
        return this;
    }

    public GameResult getGameResult() {
        return this.gameResult;
    }

    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
