package protocol.system.subprotocol;

import game.user.User;
import protocol.system.SystemProtocol;

import java.util.Map;

/**
 * 서버 to 클라 : 모든 유저에게 게임이 끝났다고 알림
 * 클라 to 서버 : -
 */
public class EndgameProtocol extends SystemProtocol {
    private String winTeam;
    private String userJobMap;

    public String getWinTeam() {
        return winTeam;
    }

    public EndgameProtocol setWinTeam(String winTeam) {
        this.winTeam = winTeam;
        return this;
    }

    public String getUserJobMap() {
        return userJobMap;
    }

    public EndgameProtocol setUserJobMap(String userJobMap) {
        this.userJobMap = userJobMap;
        return this;
    }

    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
