package com.ifsc.rautazap.wsmanagement.domain.message;

import com.ifsc.rautazap.wsmanagement.ports.input.SendMessageUseCase;
import com.ifsc.rautazap.wsmanagement.ports.output.NotifyUserPort;
import com.ifsc.rautazap.wsmanagement.ports.output.SendMessageTopicPort;
import com.ifsc.rautazap.wsmanagement.ports.output.UserPresencePort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SendMessageService implements SendMessageUseCase {

    private final UserPresencePort userPresencePort;
    private final NotifyUserPort notifyUserPort;
    private final SendMessageTopicPort sendMessageTopicPort;

    @Autowired
    public SendMessageService(UserPresencePort userPresencePort, NotifyUserPort notifyUserPort, SendMessageTopicPort sendMessageTopicPort) {
        this.userPresencePort = userPresencePort;
        this.notifyUserPort = notifyUserPort;
        this.sendMessageTopicPort = sendMessageTopicPort;
    }

    @Override
    public void sendMessage(String toUser, String message) {
        if (userPresencePort.isUserOnline(toUser)) {
            notifyUserPort.notifyUser(toUser, message);
        } else {
            // adicionar mecanismo de mensagens não lidas (entregues)
        }

        sendMessageTopicPort.publishSendMessage(toUser, message);
    }
}
