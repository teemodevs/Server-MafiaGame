package protocol.game;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import protocol.Protocol;
import protocol.ProtocolType;
import protocol.game.subprotocol.JobAllocationSubGameProtocol;
import protocol.game.subprotocol.JobSubGameProtocol;
import protocol.game.subprotocol.PhaseSubGameProtocol;
import protocol.game.subprotocol.ResultSubGameProtocol;

/**
 * 게임 관련 프로토콜 추상화 클래스
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = JobSubGameProtocol.class,    name = "JOB"),
        @JsonSubTypes.Type(value = PhaseSubGameProtocol.class,  name = "PHASE"),
        @JsonSubTypes.Type(value = ResultSubGameProtocol.class, name = "RESULT"),
        @JsonSubTypes.Type(value = JobAllocationSubGameProtocol.class,  name = "JOBALLOCATION")
})
public abstract class GameProtocol implements Protocol {
    @Override
    public ProtocolType getProtocol() {
        return ProtocolType.GAME;
    }
}
