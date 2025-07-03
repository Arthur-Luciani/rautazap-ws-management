package com.ifsc.rautazap.wsmanagement.application.ports.output;

import com.ifsc.rautazap.wsmanagement.domain.message.Message;

public interface NotifyUserPort {

    void notifyUser(Message.MessageData message);

}
