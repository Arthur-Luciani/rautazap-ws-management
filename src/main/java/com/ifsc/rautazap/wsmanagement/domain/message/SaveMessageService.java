package com.ifsc.rautazap.wsmanagement.domain.message;

import com.ifsc.rautazap.wsmanagement.ports.input.SaveMessageUseCase;
import com.ifsc.rautazap.wsmanagement.ports.output.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveMessageService implements SaveMessageUseCase {

    private final MessageRepository messageRepository;
    private final MessageFactory messageFactory;

    @Autowired
    public SaveMessageService(MessageRepository messageRepository, MessageFactory messageFactory) {
        this.messageRepository = messageRepository;
        this.messageFactory = messageFactory;
    }

    @Override
    public void saveMessage(SaveMessageCommand command) {
        Message message = messageFactory.createMessage(command.messageId(), command.fromUserId(), command.toUserId(), command.content());
        messageRepository.saveMessage(message);
    }
}
