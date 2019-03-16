package protocol;

import game.User;

public interface Protocol {
    ProtocolType getProtocol();
    void execute(User user);
}
