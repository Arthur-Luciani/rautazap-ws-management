package com.ifsc.rautazap.wsmanagement.ports.output;

public interface UserPresencePort {

    void addUserOnline(String userId);

    void removeUserOnline(String userId);

    boolean isUserOnline(String userId);
}
