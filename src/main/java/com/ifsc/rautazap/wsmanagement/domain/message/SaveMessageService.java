package com.ifsc.rautazap.wsmanagement.domain.message;

import com.ifsc.rautazap.wsmanagement.domain.user.UserFactory;
import com.ifsc.rautazap.wsmanagement.ports.input.SaveMessageUseCase;
import com.ifsc.rautazap.wsmanagement.ports.output.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveMessageService implements SaveMessageUseCase {

    private final MessageRepository messageRepository;
    private final UserFactory userFactory;

    @Autowired
    public SaveMessageService(MessageRepository messageRepository, UserFactory userFactory) {
        this.messageRepository = messageRepository;
        this.userFactory = userFactory;
    }

    @Override
    public void saveMessage(MessageDTO message) {
        Message msg = new Message(userFactory.createUser(message.fromUserId()),
                userFactory.createUser(message.toUserId()),
                message.content());

        if (msg.isDestinationUserOnline()) {
            messageRepository.saveSentMessage(msg);
            return;
        }
        messageRepository.saveUnsentMessage(msg);
    }
}
