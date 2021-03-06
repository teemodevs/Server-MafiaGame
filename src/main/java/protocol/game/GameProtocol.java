package protocol.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import protocol.Protocol;
import protocol.ProtocolType;
import protocol.game.subprotocol.*;

/**
 * 게임 관련 프로토콜 추상화 클래스
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = JobActionProtocol.class,         name = "JOB_ACTION"),
        @JsonSubTypes.Type(value = PhaseChangeProtocol.class,       name = "PHASE_CHANGE"),
        @JsonSubTypes.Type(value = ResultProtocol.class,            name = "RESULT"),
        @JsonSubTypes.Type(value = JobAllocationProtocol.class,     name = "JOB_ALLOCATION"),
        @JsonSubTypes.Type(value = UserSelectionProtocol.class,     name = "USER_SELECTION"),
        @JsonSubTypes.Type(value = DoctorHealProtocol.class,        name = "JOB_DOCTOR_HEAL"),
        @JsonSubTypes.Type(value = UserDeadProtocol.class,          name = "USER_DEAD"),
        @JsonSubTypes.Type(value = ExecuteVoteProtocol.class,       name = "VOTE_EXECUTE_AGREE"),
        @JsonSubTypes.Type(value = MafiaVoteCountProtocol.class,    name = "MAFIA_VOTE_COUNT"),
        @JsonSubTypes.Type(value = MostMafiaVotedUserProtocol.class,name = "MOST_MAFIA_VOTED_USER"),
        @JsonSubTypes.Type(value = ExecuteVoteCountProtocol.class,  name = "EXECUTE_VOTE_COUNT")
})
public abstract class GameProtocol implements Protocol {
    @Override
    public ProtocolType getProtocol() {
        return ProtocolType.GAME;
    }
}
