package protocol.game.subprotocol;

import game.user.User;
import protocol.game.GameProtocol;

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
