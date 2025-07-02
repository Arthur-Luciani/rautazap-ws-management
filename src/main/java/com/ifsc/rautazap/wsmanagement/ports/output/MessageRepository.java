package com.ifsc.rautazap.wsmanagement.ports.output;

import com.ifsc.rautazap.wsmanagement.domain.message.Message;

import java.util.List;

public interface MessageRepository {

    void saveUnsentMessage(Message message);

    void saveSentMessage(Message message);

    List<Message> getUnsentMessages(String toUserId);
}
