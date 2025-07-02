package com.ifsc.rautazap.wsmanagement.ports.output;

import com.ifsc.rautazap.wsmanagement.domain.user.UserDTO;

public interface UserOnlineTopicPort {

    void publishUserOnlie(UserDTO user);

}
