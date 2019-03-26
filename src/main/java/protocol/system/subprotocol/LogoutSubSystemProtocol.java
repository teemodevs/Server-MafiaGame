package protocol.system.subprotocol;

import game.user.User;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 다른 유저가 로그아웃 한 정보를 알림
 * 클라 to 서버 : 해당 유저가 로그아웃을 요청
 */
public class LogoutSubSystemProtocol extends SystemProtocol {
    private String userId;

    public String getUserId() {
        return userId;
    }

    public LogoutSubSystemProtocol setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
        // 유저 로그아웃 처리
        user.logout();
    }
}
