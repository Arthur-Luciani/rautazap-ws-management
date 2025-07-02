package com.ifsc.rautazap.wsmanagement.ports.output;

import com.ifsc.rautazap.wsmanagement.domain.message.Message;

public interface SaveMessageTopicPort {

    void publishSaveMessage(Message.MessageData message);
}
