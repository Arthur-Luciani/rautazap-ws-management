package com.ifsc.rautazap.wsmanagement.application.usecase;

import com.ifsc.rautazap.wsmanagement.application.ports.input.SendMessageUseCase;
import com.ifsc.rautazap.wsmanagement.application.ports.output.NotifyUserPort;
import com.ifsc.rautazap.wsmanagement.application.ports.output.SaveMessageTopicPort;
import com.ifsc.rautazap.wsmanagement.application.ports.output.UserRepository;
import com.ifsc.rautazap.wsmanagement.domain.message.Message;
import com.ifsc.rautazap.wsmanagement.application.ports.input.command.SendMessageCommand;
import com.ifsc.rautazap.wsmanagement.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService implements SendMessageUseCase {

    private final UserRepository userRepository;
    private final NotifyUserPort notifyUserPort;
    private final SaveMessageTopicPort saveMessageTopicPort;

    @Autowired
    public SendMessageService(UserRepository userRepository, NotifyUserPort notifyUserPort, SaveMessageTopicPort saveMessageTopicPort) {
        this.userRepository = userRepository;
        this.notifyUserPort = notifyUserPort;
        this.saveMessageTopicPort = saveMessageTopicPort;
    }

    @Override
    public void sendMessage(SendMessageCommand command) {
        User from = userRepository.findUserById(new User.UserId(command.fromUserId()));
        User to = userRepository.findUserById(new User.UserId(command.toUserId()));
        Message message = from.sendMessage(to, command.content());

        if (message.isDestinationUserOnline()) {
            notifyUserPort.notifyUser(message.snapshot());
        }
        saveMessageTopicPort.publishSaveMessage(message.snapshot());
    }
}
