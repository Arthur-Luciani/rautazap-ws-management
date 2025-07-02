package com.ifsc.rautazap.wsmanagement.domain.user;

import com.ifsc.rautazap.wsmanagement.ports.input.UserPresenceUseCase;
import com.ifsc.rautazap.wsmanagement.ports.output.UserOnlineTopicPort;
import com.ifsc.rautazap.wsmanagement.ports.output.UserPresencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConnectService implements UserPresenceUseCase {

    private final UserPresencePort userPresencePort;
    private final UserOnlineTopicPort userOnlineTopicPort;

    @Autowired
    public UserConnectService(UserPresencePort userPresencePort, UserOnlineTopicPort userOnlineTopicPort) {
        this.userPresencePort = userPresencePort;
        this.userOnlineTopicPort = userOnlineTopicPort;
    }

    @Override
    public void onUserConnected(String userId) {
        userPresencePort.addUserOnline(userId);
        User user = new User(userId);
        UserDTO dto = user.toDTO();
        userOnlineTopicPort.publishUserOnlie(dto);
    }

    @Override
    public void onUserDisconnected(String userId) {
        userPresencePort.removeUserOnline(userId);
    }
}
