package com.ifsc.rautazap.wsmanagement.ports.input;

public interface UserPresenceUseCase {

    void onUserConnected(String userId);

    void onUserDisconnected(String userId);

}
