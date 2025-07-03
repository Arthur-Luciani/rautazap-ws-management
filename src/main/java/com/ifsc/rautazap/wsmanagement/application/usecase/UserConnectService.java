package com.ifsc.rautazap.wsmanagement.application.usecase;

import com.ifsc.rautazap.wsmanagement.application.ports.input.UserPresenceUseCase;
import com.ifsc.rautazap.wsmanagement.application.ports.output.UserOnlineTopicPort;
import com.ifsc.rautazap.wsmanagement.application.ports.output.UserPresencePort;
import com.ifsc.rautazap.wsmanagement.domain.user.UserId;
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
    public void onUserConnected(UserId userId) {
        userPresencePort.addUserOnline(userId);
        userOnlineTopicPort.publishUserOnline(userId);
    }

    @Override
    public void onUserDisconnected(UserId userId) {
        userPresencePort.removeUserOnline(userId);
    }
}
