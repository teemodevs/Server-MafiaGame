package protocol.game.subprotocol;

import game.GameRoom;
import game.user.User;
import protocol.Protocol;
import protocol.game.GameProtocol;

/**
 * 서버 to 클라 : -
 * 클라 to 서버 : 처형투표 찬/반 요청
 */
public class ExecuteVoteProtocol extends GameProtocol {
    private boolean agree;

    public ExecuteVoteProtocol setAgree(boolean agree) {
        this.agree = agree;
        return this;
    }

    public boolean isAgree() {
        return agree;
    }

    /**
     * 찬성 시 마피아 투표에서 가장 많은 투표를 받은 사람의 처형 투표 수를 올림
     */
    @Override
    public void execute(User user) {
        System.out.println(this.getClass().getSimpleName() + ".execute()");

        // Phase 중 1회만 찬/반 투표 가능
        if(!user.getUserGameState().getVoteContext().isExecuteVoteActivate())
            return;

        if(this.isAgree()) {
            // 가장 투표를 많이 받은 유저에 대해 exectueVote 카운트를 올림
            User mostMafiaVotedUser = user.getGameRoom().getGameContext().getMostMafiaVotedUser();
            mostMafiaVotedUser.getUserGameState().getVoteContext().executeVote();

            Protocol protocol = new ExecuteVoteCountProtocol()
                    .setUserId(mostMafiaVotedUser.getUserId())
                    .setExecuteVoteCount(mostMafiaVotedUser.getUserGameState().getVoteContext().getExecuteVotedCount());

            GameRoom gameRoom = mostMafiaVotedUser.getGameRoom();
            gameRoom.sendProtocol(protocol);
        }
        // ExecuteVote의 경우 Phase 당 1회만 가능
        user.getUserGameState().getVoteContext().setExecuteVoteActivate(false);
    }
}
