package com.ifsc.rautazap.wsmanagement.domain.message;

import com.ifsc.rautazap.wsmanagement.domain.user.User;

import java.time.LocalDateTime;
import java.util.UUID;

public class Message {

    private final String id;
    private final User fromUser;
    private final User toUser;
    private final String content;
    private final LocalDateTime timestamp;

    public Message(User fromUser, User toUser, String content) {
        this(UUID.randomUUID().toString(), fromUser, toUser, content, LocalDateTime.now());
    }

    public Message(String id, User fromUser, User toUser, String content, LocalDateTime timestamp) {
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.content = content;
        this.timestamp = timestamp;
        this.verifyMessageContent();
        this.verifyUsers();
    }

    private void verifyMessageContent() {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be null or empty");
        }
    }

    private void verifyUsers() {
        if (fromUser == null || toUser == null) {
            throw new IllegalArgumentException("Both sender and receiver must be specified");
        }
    }

    public boolean isDestinationUserOnline() {
        return toUser.isOnline();
    }

    public MessageData snapshot() {
        return new MessageData(
                this.id,
                this.fromUser.snapshot().value(),
                this.toUser.snapshot().value(),
                this.content,
                this.timestamp,
                this.isDestinationUserOnline());
    }

    public record MessageData(
            String id,
            String fromUserId,
            String toUserId,
            String content,
            LocalDateTime timestamp,
            boolean isDestinationUserOnline
    ) {
    }
}
