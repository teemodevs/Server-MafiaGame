package protocol.game.subprotocol;

import game.user.User;
import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : 유저에게 할당된 직업을 통보
 * 클라 to 서버 : -
 **/
public class JobAllocationSubGameProtocol extends GameProtocol {

    private String jobName;

    public String getJobName() {
        return jobName;
    }

    public JobAllocationSubGameProtocol setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
