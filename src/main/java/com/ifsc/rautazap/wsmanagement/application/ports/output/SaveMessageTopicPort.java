package com.ifsc.rautazap.wsmanagement.application.ports.output;

import com.ifsc.rautazap.wsmanagement.domain.message.Message;

public interface SaveMessageTopicPort {

    void publishSaveMessage(Message.MessageData message);
}
