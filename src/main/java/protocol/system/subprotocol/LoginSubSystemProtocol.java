package protocol.system.subprotocol;

import game.GameRoom;
import game.User;
import protocol.system.SystemProtocol;

public class LoginSubSystemProtocol extends SystemProtocol {
    private String userId;
    private String password;
    private boolean isLoginSuccess;
    private String loginFailedReason;

    public String getUserId() {
        return userId;
    }

    public LoginSubSystemProtocol setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public LoginSubSystemProtocol setPassword(String password) {
        this.password = password;
        return this;
    }

    public boolean isLoginSuccess() {
        return isLoginSuccess;
    }

    public LoginSubSystemProtocol setLoginSuccess(boolean loginSuccess) {
        isLoginSuccess = loginSuccess;
        return this;
    }

    public String getLoginFailedReason() {
        return loginFailedReason;
    }

    public LoginSubSystemProtocol setLoginFailedReason(String loginFailedReason) {
        this.loginFailedReason = loginFailedReason;
        return this;
    }
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");

        if(this.userId.equals("u1")) // 인증하는 부분
            GameRoom.getInstance().addUser(user.setUserId(userId));
        if(this.userId.equals("u2")) // 인증하는 부분
            GameRoom.getInstance().addUser(user.setUserId(userId));
        if(this.userId.equals("u3")) // 인증하는 부분
            GameRoom.getInstance().addUser(user.setUserId(userId));
        if(this.userId.equals("u4")) // 인증하는 부분
            GameRoom.getInstance().addUser(user.setUserId(userId));
        if(this.userId.equals("u5")) // 인증하는 부분
            GameRoom.getInstance().addUser(user.setUserId(userId));
        if(this.userId.equals("u6")) // 인증하는 부분
            GameRoom.getInstance().addUser(user.setUserId(userId));

        this.loginFailedReason = null;
        this.isLoginSuccess = true;
        user.sendMessage(this);
    }
}
