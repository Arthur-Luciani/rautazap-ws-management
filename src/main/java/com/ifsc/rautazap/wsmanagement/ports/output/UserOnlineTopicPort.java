package com.ifsc.rautazap.wsmanagement.ports.output;

import com.ifsc.rautazap.wsmanagement.domain.user.UserId;

public interface UserOnlineTopicPort {

    void publishUserOnline(UserId user);

}
