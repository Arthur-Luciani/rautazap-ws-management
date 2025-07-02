package com.ifsc.rautazap.wsmanagement.domain.message;

import com.ifsc.rautazap.wsmanagement.domain.user.User;
import com.ifsc.rautazap.wsmanagement.domain.user.UserFactory;
import com.ifsc.rautazap.wsmanagement.ports.input.SendMessageUseCase;
import com.ifsc.rautazap.wsmanagement.ports.output.NotifyUserPort;
import com.ifsc.rautazap.wsmanagement.ports.output.SendMessageTopicPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService implements SendMessageUseCase {

    private final UserFactory userFactory;
    private final NotifyUserPort notifyUserPort;
    private final SendMessageTopicPort sendMessageTopicPort;

    @Autowired
    public SendMessageService(UserFactory userFactory, NotifyUserPort notifyUserPort, SendMessageTopicPort sendMessageTopicPort) {
        this.userFactory = userFactory;
        this.notifyUserPort = notifyUserPort;
        this.sendMessageTopicPort = sendMessageTopicPort;
    }

    @Override
    public void sendMessage(String fromUserId, String toUserId, String content) {
        User fromUser = userFactory.createUser(toUserId);
        User toUser = userFactory.createUser(toUserId);
        Message message  = new Message(fromUser, toUser, content);

        if (message.isDestinationUserOnline()) {
            notifyUserPort.notifyUser(fromUserId, toUserId, content);
        }

        sendMessageTopicPort.publishSaveMessage(message.toDTO());
    }
}
