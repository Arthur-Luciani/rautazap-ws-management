package com.ifsc.rautazap.wsmanagement.application.ports.input;

import com.ifsc.rautazap.wsmanagement.domain.user.UserId;

public interface UserPresenceUseCase {

    void onUserConnected(UserId userId);

    void onUserDisconnected(UserId userId);

}
