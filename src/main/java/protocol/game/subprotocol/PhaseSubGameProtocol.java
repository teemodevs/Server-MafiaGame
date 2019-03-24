package protocol.game.subprotocol;

import game.user.User;
import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : 모든 유저에게 Phase 변경을 통보
 * 클라 to 서버 : -
 **/
public class PhaseSubGameProtocol extends GameProtocol {
    private String phaseName;

    public PhaseSubGameProtocol setPhaseName(String phaseName) {
        this.phaseName = phaseName;
        return this;
    }

    public String getPhaseName() {
        return this.phaseName;
    }

    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
