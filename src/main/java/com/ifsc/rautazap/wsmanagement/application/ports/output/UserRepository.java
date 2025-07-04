package com.ifsc.rautazap.wsmanagement.application.ports.output;

import com.ifsc.rautazap.wsmanagement.domain.user.User;

public interface UserRepository {

    void saveUser(User user);

    User findUserById(User.UserId userId);
}
