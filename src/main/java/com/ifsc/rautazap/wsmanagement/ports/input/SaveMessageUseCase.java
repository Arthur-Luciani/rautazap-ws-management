package com.ifsc.rautazap.wsmanagement.ports.input;

import com.ifsc.rautazap.wsmanagement.domain.message.SaveMessageCommand;

public interface SaveMessageUseCase {

    void saveMessage(SaveMessageCommand command);
}
