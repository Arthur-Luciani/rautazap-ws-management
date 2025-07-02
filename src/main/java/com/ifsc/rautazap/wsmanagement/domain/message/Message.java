package com.ifsc.rautazap.wsmanagement.domain.message;

import com.ifsc.rautazap.wsmanagement.domain.user.User;

public class Message {

    private String id;
    private User fromUser;
    private User toUser;
    private String content;

    public Message(User fromUser, User toUser, String content) {
        this.verifyMessageContent(content);
        this.fromUser = fromUser;
        this.toUser = toUser;
        this.content = content;
    }

    private void verifyMessageContent(String content) {
        if (content == null || content.isEmpty()) {
            throw new IllegalArgumentException("Message content cannot be null or empty");
        }
    }

    public boolean isDestinationUserOnline() {
        return toUser.isOnline();
    }

    public MessageDTO toDTO() {
        return new MessageDTO(fromUser.getId(), toUser.getId(), content, isDestinationUserOnline());
    }
}
