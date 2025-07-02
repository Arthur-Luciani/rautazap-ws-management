package com.ifsc.rautazap.wsmanagement.domain.user;

import com.ifsc.rautazap.wsmanagement.domain.message.Message;
import com.ifsc.rautazap.wsmanagement.ports.input.SendMessageUseCase;
import com.ifsc.rautazap.wsmanagement.ports.input.UserOnlineUseCase;
import com.ifsc.rautazap.wsmanagement.ports.output.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserOnlineService implements UserOnlineUseCase {

    private final SendMessageUseCase sendMessageUseCase;
    private final MessageRepository messageRepository;

    @Autowired
    public UserOnlineService(SendMessageUseCase sendMessageUseCase, MessageRepository messageRepository) {
        this.sendMessageUseCase = sendMessageUseCase;
        this.messageRepository = messageRepository;
    }

    @Override
    public void userOnline(UserDTO user) {
        messageRepository.getUnsentMessages(user.id())
                .stream()
                .map(Message::toDTO)
                .forEach(message -> sendMessageUseCase.sendMessage(message.fromUserId(), message.toUserId(), message.content()));
    }
}
