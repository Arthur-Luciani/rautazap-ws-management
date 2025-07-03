package com.ifsc.rautazap.wsmanagement.application.usecase;

import com.ifsc.rautazap.wsmanagement.application.ports.input.SendMessageUseCase;
import com.ifsc.rautazap.wsmanagement.application.ports.output.NotifyUserPort;
import com.ifsc.rautazap.wsmanagement.application.ports.output.SaveMessageTopicPort;
import com.ifsc.rautazap.wsmanagement.domain.message.Message;
import com.ifsc.rautazap.wsmanagement.domain.message.MessageFactory;
import com.ifsc.rautazap.wsmanagement.domain.message.SendMessageCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService implements SendMessageUseCase {

    private final MessageFactory messageFactory;
    private final NotifyUserPort notifyUserPort;
    private final SaveMessageTopicPort saveMessageTopicPort;

    @Autowired
    public SendMessageService(MessageFactory messageFactory, NotifyUserPort notifyUserPort, SaveMessageTopicPort saveMessageTopicPort) {
        this.messageFactory = messageFactory;
        this.notifyUserPort = notifyUserPort;
        this.saveMessageTopicPort = saveMessageTopicPort;
    }

    @Override
    public void sendMessage(SendMessageCommand command) {
        Message message = messageFactory.createMessage(command.fromUserId(), command.toUserId(), command.content());

        if (message.isDestinationUserOnline()) {
            notifyUserPort.notifyUser(message.snapshot());
        }
        saveMessageTopicPort.publishSaveMessage(message.snapshot());
    }
}
