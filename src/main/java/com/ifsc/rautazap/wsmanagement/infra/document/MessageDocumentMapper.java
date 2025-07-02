package com.ifsc.rautazap.wsmanagement.infra.document;

import com.ifsc.rautazap.wsmanagement.domain.message.Message;
import com.ifsc.rautazap.wsmanagement.domain.message.MessageDTO;
import com.ifsc.rautazap.wsmanagement.domain.user.User;
import org.springframework.stereotype.Component;

@Component
public class MessageDocumentMapper {

    public MessageDocument toDocument(Message message) {
        MessageDTO dto = message.toDTO();
        return new MessageDocument(dto.fromUserId(), dto.toUserId(), dto.content(), dto.isToUserOnline());
    }

    public MessageDocument toDocument(MessageDTO dto) {
        return new MessageDocument(dto.fromUserId(), dto.toUserId(), dto.content(), dto.isToUserOnline());
    }
}
