package com.ifsc.rautazap.wsmanagement.domain.user;

import lombok.Getter;

@Getter
public class User {

    private String id;
    private boolean online;

    public User(String id) {
        this(id, true);
    }

    public User(String id, boolean online) {
        this.id = id;
        this.online = online;
    }

    public UserId toDTO() {
        return new UserId(id);
    }

}
