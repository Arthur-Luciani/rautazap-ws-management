package com.ifsc.rautazap.wsmanagement.domain.message;

import com.ifsc.rautazap.wsmanagement.domain.user.User;

import java.time.LocalDateTime;

public class Message {

    private String id;
    private User fromUser;
    private User toUser;
    private String content;
    private LocalDateTime timestamp;

    public Message(String id, User fromUser, User toUser, String content, LocalDateTime timestamp) {
        this.verifyMessageContent(content);
        this.id = id;
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.content = content;
        this.timestamp = timestamp;
    }

    private void verifyMessageContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be null or empty");
        }
    }

    public boolean isDestinationUserOnline() {
        return toUser.isOnline();
    }

    public MessageData snapshot() {
        return new MessageData(
                this.id,
                this.fromUser.getId(),
                this.toUser.getId(),
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
