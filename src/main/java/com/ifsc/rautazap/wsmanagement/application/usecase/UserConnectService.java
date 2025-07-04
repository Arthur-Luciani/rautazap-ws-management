package com.ifsc.rautazap.wsmanagement.application.usecase;

import com.ifsc.rautazap.wsmanagement.application.ports.input.UserPresenceUseCase;
import com.ifsc.rautazap.wsmanagement.application.ports.output.UserOnlineTopicPort;
import com.ifsc.rautazap.wsmanagement.application.ports.output.UserRepository;
import com.ifsc.rautazap.wsmanagement.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserConnectService implements UserPresenceUseCase {

    private final UserRepository userPresencePort;
    private final UserOnlineTopicPort userOnlineTopicPort;

    @Autowired
    public UserConnectService(UserRepository userPresencePort, UserOnlineTopicPort userOnlineTopicPort) {
        this.userPresencePort = userPresencePort;
        this.userOnlineTopicPort = userOnlineTopicPort;
    }

    @Override
    public void onUserConnected(User.UserId userId) {
        userPresencePort.saveUser(new User(userId.value(), true));
        userOnlineTopicPort.publishUserOnline(userId);
    }

    @Override
    public void onUserDisconnected(User.UserId userId) {
        userPresencePort.saveUser(new User(userId.value(), false));
    }
}
