package com.ifsc.rautazap.wsmanagement.application.ports.output;

import com.ifsc.rautazap.wsmanagement.domain.message.Message;

import java.util.List;

public interface MessageRepository {

    void saveMessage(Message message);

    List<Message> getUnsentMessages(String toUserId);
}
