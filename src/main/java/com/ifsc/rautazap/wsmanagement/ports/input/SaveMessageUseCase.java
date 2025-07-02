package com.ifsc.rautazap.wsmanagement.ports.input;

import com.ifsc.rautazap.wsmanagement.domain.message.MessageDTO;

public interface SaveMessageUseCase {

    void saveMessage(MessageDTO message);
}
