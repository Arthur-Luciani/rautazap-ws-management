package com.ifsc.rautazap.wsmanagement.ports.output;

import com.ifsc.rautazap.wsmanagement.domain.message.MessageDTO;

public interface SendMessageTopicPort {

    void publishSaveMessage(MessageDTO message);
}
