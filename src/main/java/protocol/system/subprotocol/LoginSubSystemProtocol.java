package protocol.system.subprotocol;

import java.util.List;

import game.GameRoom;
import game.user.User;
import protocol.system.SystemProtocol;
/**
 * 서버 to 클라 : 다른 유저가 로그인 한 정보를 알림
 * 클라 to 서버 : 해당 유저가 로그인을 요청
 **/
public class LoginSubSystemProtocol extends SystemProtocol {
    private String userId;
    private String password;
    private boolean isLoginSuccess;
    private String loginFailedReason;
    private List<String> loginUsers; // 이미 로그인한 유저의 리스트

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
    
    public List<String> getLoginUsers() {
		return loginUsers;
	}

	public LoginSubSystemProtocol setLoginUsers(List<String> loginUsers) {
		this.loginUsers = loginUsers;
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
        
        this.setLoginUsers(GameRoom.getInstance().getLoginUserList());
        
        GameRoom.getInstance().sendProtocol(this);
    }
}
