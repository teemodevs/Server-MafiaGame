package protocol.game.subprotocol;

import game.user.User;
import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : -
 * 클라 to 서버 : 해당 유저에 대한 직업 행동을 요청
 **/
public class JobSubGameProtocol extends GameProtocol {
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
