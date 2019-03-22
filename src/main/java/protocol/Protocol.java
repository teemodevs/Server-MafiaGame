package protocol;

import game.user.User;

public interface Protocol {
    ProtocolType getProtocol();
    void execute(User user);
}
