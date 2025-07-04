package com.ifsc.rautazap.wsmanagement.domain.message;

import com.ifsc.rautazap.wsmanagement.domain.user.User;
import com.ifsc.rautazap.wsmanagement.application.ports.output.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class MessageFactory {

    private final UserRepository userRepository;

    @Autowired
    public MessageFactory(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Message createMessage(String fromUserId, String toUserId, String content) {
        return createMessage(UUID.randomUUID().toString(), fromUserId, toUserId, content);
    }

    public Message createMessage(String messageId, String fromUserId, String toUserId, String content) {
        User fromUser = userRepository.findUserById(new User.UserId(fromUserId));
        User toUser = userRepository.findUserById(new User.UserId(toUserId));
        return new Message(messageId, fromUser, toUser, content, LocalDateTime.now());
    }
}
