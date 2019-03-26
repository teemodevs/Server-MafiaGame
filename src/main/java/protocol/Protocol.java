package protocol;

import game.user.User;

/**
 * 프로토콜 최상위 추상화 인터페이스
 */
public interface Protocol {
    ProtocolType getProtocol();
    void execute(User user);
}
