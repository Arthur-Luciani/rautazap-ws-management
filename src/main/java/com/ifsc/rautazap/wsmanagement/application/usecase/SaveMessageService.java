package com.ifsc.rautazap.wsmanagement.application.usecase;

import com.ifsc.rautazap.wsmanagement.application.ports.input.SaveMessageUseCase;
import com.ifsc.rautazap.wsmanagement.application.ports.output.MessageRepository;
import com.ifsc.rautazap.wsmanagement.application.ports.output.UserRepository;
import com.ifsc.rautazap.wsmanagement.domain.message.Message;
import com.ifsc.rautazap.wsmanagement.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SaveMessageService implements SaveMessageUseCase {

    private final MessageRepository messageRepository;
    private final UserRepository userRepository;

    @Autowired
    public SaveMessageService(MessageRepository messageRepository, UserRepository userRepository) {
        this.messageRepository = messageRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveMessage(Message.MessageData command) {
        User fromUser = userRepository.findUserById(new User.UserId(command.fromUserId()));
        User toUser = userRepository.findUserById(new User.UserId(command.toUserId()));
        Message message = fromUser.sendMessage(toUser, command.content());

        messageRepository.saveMessage(message);
    }
}
