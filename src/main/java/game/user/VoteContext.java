package game.user;

/**
 * 마피아 투표, 처형 찬반 투표 등 투표 관련 정보 저장 클래스
 */
public class VoteContext {
    private int     mafiaVotedCount;        // 마피아 투표 받은 갯수
    private int     executeVotedCount;      // 처형 투표 찬성 갯수
    private boolean mafiaVoteActivate;      // 마피아 투표 가능 여부
    private boolean executeVoteActivate;    // 처형 투표 가능 여부

    /**
     * 투표 받은 상태 초기화
     */
    public void init() {
        this.mafiaVotedCount     = 0;
        this.executeVotedCount   = 0;
        this.mafiaVoteActivate   = true;
        this.executeVoteActivate = true;
    }

    /**
     * 해당 유저가 받은 마피아 투표 수 반환
     * @return mafiaVotedCount int 해당 유저가 받은 마피아 투표 수
     */
    public int getMafiaVotedCount() {
        return this.mafiaVotedCount;
    }

    /**
     * 해당 유저가 받은 처형 찬성 수 반환
     * @return executeVotedCount int 해당 유저가 받은 처형 투표 찬성 수
     */
    public int getExecuteVotedCount() {
        return this.executeVotedCount;
    }

    public void mafiaVote() {
        this.mafiaVotedCount++;
    }

    public void executeVote() {
        this.executeVotedCount++;
    }

    public boolean isMafiaVoteActivate() {
        return mafiaVoteActivate;
    }

    public VoteContext setMafiaVoteActivate(boolean mafiaVoteActivate) {
        this.mafiaVoteActivate = mafiaVoteActivate;
        return this;
    }

    public boolean isExecuteVoteActivate() {
        return executeVoteActivate;
    }

    public VoteContext setExecuteVoteActivate(boolean executeVoteActivate) {
        this.executeVoteActivate = executeVoteActivate;
        return this;
    }
}
