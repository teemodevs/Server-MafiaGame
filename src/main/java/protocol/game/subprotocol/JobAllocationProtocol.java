package protocol.game.subprotocol;

import game.user.User;
import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : 유저에게 할당된 직업을 통보
 * 클라 to 서버 : -
 */
public class JobAllocationProtocol extends GameProtocol {
    private String jobName; // 직업명

    public String getJobName() {
        return jobName;
    }

    public JobAllocationProtocol setJobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");
    }
}
