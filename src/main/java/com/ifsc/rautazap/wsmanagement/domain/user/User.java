package com.ifsc.rautazap.wsmanagement.domain.user;

public class User {

    private String id;
    private boolean online;

    public User(String id, boolean online) {
        this.id = id;
        this.online = online;
    }

    public UserId snapshot() {
        return new UserId(id);
    }

    public record UserId(String value) {
    }

    public boolean isOnline() {
        return online;
    }
}
