package com.ifsc.rautazap.wsmanagement.application.ports.output;

import com.ifsc.rautazap.wsmanagement.domain.user.User;

public interface UserOnlineTopicPort {

    void publishUserOnline(User.UserId user);

}
