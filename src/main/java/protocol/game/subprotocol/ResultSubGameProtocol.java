package protocol.game.subprotocol;

import game.user.User;
import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : 게임 결과를 유저들에게 통보
 * 클라 to 서버 : -
 **/
public class ResultSubGameProtocol extends GameProtocol {
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
