package com.ifsc.rautazap.wsmanagement.domain.message;

import com.ifsc.rautazap.wsmanagement.domain.user.User;
import com.ifsc.rautazap.wsmanagement.domain.user.UserId;
import com.ifsc.rautazap.wsmanagement.ports.output.UserPresencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class MessageFactory {

    private final UserPresencePort userPresencePort;

    @Autowired
    public MessageFactory(UserPresencePort userPresencePort) {
        this.userPresencePort = userPresencePort;
    }

    public Message createMessage(String fromUserId, String toUserId, String content) {
        return createMessage(UUID.randomUUID().toString(), fromUserId, toUserId, content);
    }

    public Message createMessage(String messageId, String fromUserId, String toUserId, String content) {
        User fromUser = new User(fromUserId, userPresencePort.isUserOnline(new UserId(fromUserId)));
        User toUser = new User(toUserId, userPresencePort.isUserOnline(new UserId(toUserId)));
        return new Message(messageId, fromUser, toUser, content, LocalDateTime.now());
    }
}
