package com.ifsc.rautazap.wsmanagement.application.usecase;

import com.ifsc.rautazap.wsmanagement.application.ports.input.UserOnlineUseCase;
import com.ifsc.rautazap.wsmanagement.application.ports.output.MessageRepository;
import com.ifsc.rautazap.wsmanagement.application.ports.output.NotifyUserPort;
import com.ifsc.rautazap.wsmanagement.domain.message.Message;
import com.ifsc.rautazap.wsmanagement.domain.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserOnlineService implements UserOnlineUseCase {

    private final NotifyUserPort notifyUserPort;
    private final MessageRepository messageRepository;

    @Autowired
    public UserOnlineService(NotifyUserPort notifyUserPort, MessageRepository messageRepository) {
        this.notifyUserPort = notifyUserPort;
        this.messageRepository = messageRepository;
    }

    @Override
    public void userOnline(User.UserId userId) {
        List<Message> list = messageRepository.getUnsentMessages(userId.value());
        for (Message message : list) {
            notifyUserPort.notifyUser(message.snapshot());
            messageRepository.saveMessage(message);
        }
    }
}
