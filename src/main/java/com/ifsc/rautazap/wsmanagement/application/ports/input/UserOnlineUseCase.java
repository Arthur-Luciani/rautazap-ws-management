package com.ifsc.rautazap.wsmanagement.application.ports.input;

import com.ifsc.rautazap.wsmanagement.domain.user.UserId;

public interface UserOnlineUseCase {

    void userOnline(UserId user);
}
