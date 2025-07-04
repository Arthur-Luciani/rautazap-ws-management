package com.ifsc.rautazap.wsmanagement.infra.adapters.output.mongo;

import com.ifsc.rautazap.wsmanagement.application.ports.output.MessageRepository;
import com.ifsc.rautazap.wsmanagement.application.ports.output.UserRepository;
import com.ifsc.rautazap.wsmanagement.domain.message.Message;
import com.ifsc.rautazap.wsmanagement.domain.user.User;
import com.ifsc.rautazap.wsmanagement.infra.mongo.MessageDocument;
import com.ifsc.rautazap.wsmanagement.infra.mongo.MongoMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageRepositoryAdapter implements MessageRepository {

    private final MongoMessageRepository repository;
    private final UserRepository userRepository;

    @Autowired
    public MessageRepositoryAdapter(MongoMessageRepository repository, UserRepository userRepository) {
        this.repository = repository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveMessage(Message message) {
        MessageDocument document = MessageDocument.fromMessage(message.snapshot());
        repository.save(document);
    }

    @Override
    public List<Message> getUnsentMessages(String toUserId) {
        User toUser = userRepository.findUserById(new User.UserId(toUserId));
        return repository.findByToUserIdAndDeliveredFalseOrderByTimestampDesc(toUserId)
                .stream()
                .map(document -> {
                    User fromUser = userRepository.findUserById(new User.UserId(document.getFromUserId()));
                    return new Message(document.getId(), fromUser, toUser, document.getContent(), document.getTimestamp());
                }).toList();
    }
}
