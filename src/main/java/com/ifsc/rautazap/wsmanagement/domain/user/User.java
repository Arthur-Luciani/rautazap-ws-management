package com.ifsc.rautazap.wsmanagement.domain.user;

import com.ifsc.rautazap.wsmanagement.domain.message.Message;
import lombok.Getter;

public class User {

    private final String id;
    @Getter
    private boolean online;

    public User(String id, boolean online) {
        this.id = id;
        this.online = online;
    }

    public void goOnline() {
        this.online = true;
    }

    public void goOffline() {
        this.online = false;
    }

    public Message sendMessage(User toUser, String content) {
        return new Message(this, toUser, content);
    }

    public UserId snapshot() {
        return new UserId(id);
    }

    public record UserId(String value) {
    }
}
