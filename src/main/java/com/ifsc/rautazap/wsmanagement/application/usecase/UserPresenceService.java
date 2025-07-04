package com.ifsc.rautazap.wsmanagement.application.usecase;

import com.ifsc.rautazap.wsmanagement.application.ports.input.UserPresenceUseCase;
import com.ifsc.rautazap.wsmanagement.application.ports.output.UserOnlineTopicPort;
import com.ifsc.rautazap.wsmanagement.application.ports.output.UserRepository;
import com.ifsc.rautazap.wsmanagement.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserPresenceService implements UserPresenceUseCase {

    private final UserRepository userRepository;
    private final UserOnlineTopicPort userOnlineTopicPort;

    @Autowired
    public UserPresenceService(UserRepository userRepository, UserOnlineTopicPort userOnlineTopicPort) {
        this.userRepository = userRepository;
        this.userOnlineTopicPort = userOnlineTopicPort;
    }

    @Override
    public void onUserConnected(User.UserId userId) {
        User user = userRepository.findUserById(userId);
        user.goOnline();

        userRepository.saveUser(user);
        userOnlineTopicPort.publishUserOnline(userId);
    }

    @Override
    public void onUserDisconnected(User.UserId userId) {
        User user = userRepository.findUserById(userId);
        user.goOffline();

        userRepository.saveUser(user);
    }
}
