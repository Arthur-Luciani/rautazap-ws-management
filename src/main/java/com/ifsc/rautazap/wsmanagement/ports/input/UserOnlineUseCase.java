package com.ifsc.rautazap.wsmanagement.ports.input;

import com.ifsc.rautazap.wsmanagement.domain.user.UserDTO;

public interface UserOnlineUseCase {

    void userOnline(UserDTO user);
}
