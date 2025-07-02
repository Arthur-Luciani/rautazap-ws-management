package com.ifsc.rautazap.wsmanagement.ports.input;

import com.ifsc.rautazap.wsmanagement.domain.message.Message;

public interface SaveMessageUseCase {

    void saveMessage(Message.MessageData command);
}
