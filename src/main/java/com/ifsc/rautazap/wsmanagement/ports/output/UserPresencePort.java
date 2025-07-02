package com.ifsc.rautazap.wsmanagement.ports.output;

import com.ifsc.rautazap.wsmanagement.domain.user.UserId;

public interface UserPresencePort {

    void addUserOnline(UserId userId);

    void removeUserOnline(UserId userId);

    boolean isUserOnline(UserId userId);
}
