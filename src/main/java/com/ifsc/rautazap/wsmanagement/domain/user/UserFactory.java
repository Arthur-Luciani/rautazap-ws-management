package com.ifsc.rautazap.wsmanagement.domain.user;

import com.ifsc.rautazap.wsmanagement.ports.output.UserPresencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserFactory {

    private final UserPresencePort userPresencePort;

    @Autowired
    public UserFactory(UserPresencePort userPresencePort) {
        this.userPresencePort = userPresencePort;
    }

    public User createUser(String userId) {
        return new User(userId, userPresencePort.isUserOnline(userId));
    }
}
