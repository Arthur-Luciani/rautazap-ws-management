package com.ifsc.rautazap.wsmanagement.application.ports.input;

import com.ifsc.rautazap.wsmanagement.domain.user.User;

public interface UserOnlineUseCase {

    void userOnline(User.UserId user);
}
