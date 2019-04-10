package game.user;

/**
 * 마피아 지정, 의사 지정 등 직업 행동 관련 저장 정보
 */
public class FlagContext {
    private boolean mafiaSelection;  // 마피아 지정 여부
    private boolean doctorSelection; // 의사 지정 여부

    FlagContext() {
        this.init();
    }

    public boolean isMafiaSelection() {
        return mafiaSelection;
    }

    public FlagContext setMafiaSelection(boolean mafiaSelection) {
        this.mafiaSelection = mafiaSelection;
        return this;
    }

    public boolean isDoctorSelection() {
        return doctorSelection;
    }

    public FlagContext setDoctorSelection(boolean doctorSelection) {
        this.doctorSelection = doctorSelection;
        return this;
    }

    /**
     * 지정 받은 상태 초기화
     */
    public void init() {
        this.mafiaSelection = false;
        this.doctorSelection = false;
    }

}
