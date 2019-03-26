package protocol.system.subprotocol;

import game.user.User;
import protocol.system.SystemProtocol;

/**
 * 서버 to 클라 : 다른 유저가 로그인 한 정보를 알림
 * 클라 to 서버 : 해당 유저가 로그인을 요청
 */
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

        // 로그인 성공
        if((this.userId.equals("u1") && this.password.equals("p1")) 
		|| (this.userId.equals("u2") && this.password.equals("p2"))
		|| (this.userId.equals("u3") && this.password.equals("p3"))
		|| (this.userId.equals("u4") && this.password.equals("p4"))
		|| (this.userId.equals("u5") && this.password.equals("p5"))
		|| (this.userId.equals("u6") && this.password.equals("p6"))) {
        	this.isLoginSuccess = true;
        }
        
        // 로그인 실패
        else {
        	this.isLoginSuccess = false;
        	this.loginFailedReason = "Wrong ID or Password";        	
        }
        	
        user.setUserId(this.userId);
        user.sendProtocol(this);
    }
}
