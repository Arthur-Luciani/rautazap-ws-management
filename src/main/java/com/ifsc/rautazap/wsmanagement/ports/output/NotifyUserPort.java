package com.ifsc.rautazap.wsmanagement.ports.output;

public interface NotifyUserPort {

    void notifyUser(String fromUserId, String toUserId, String message);
}
