package com.ifsc.rautazap.wsmanagement.application.ports.input;

import com.ifsc.rautazap.wsmanagement.domain.user.User;

public interface UserPresenceUseCase {

    void onUserConnected(User.UserId userId);

    void onUserDisconnected(User.UserId userId);

}
