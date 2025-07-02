package com.ifsc.rautazap.wsmanagement.adapters.output.mongo;

import com.ifsc.rautazap.wsmanagement.domain.message.Message;
import com.ifsc.rautazap.wsmanagement.domain.user.User;
import com.ifsc.rautazap.wsmanagement.infra.document.MessageDocument;
import com.ifsc.rautazap.wsmanagement.infra.document.MessageDocumentMapper;
import com.ifsc.rautazap.wsmanagement.infra.document.MessageMongoRepository;
import com.ifsc.rautazap.wsmanagement.ports.output.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MessageRepositoryAdapter implements MessageRepository {

    private final MessageMongoRepository repository;
    private final MessageDocumentMapper documentMapper;

    @Autowired
    public MessageRepositoryAdapter(MessageMongoRepository repository, MessageDocumentMapper documentMapper) {
        this.repository = repository;
        this.documentMapper = documentMapper;
    }

    @Override
    public void saveUnsentMessage(Message message) {
        MessageDocument document = documentMapper.toDocument(message);
        repository.save(document);
    }

    @Override
    public void saveSentMessage(Message message) {
        MessageDocument document = documentMapper.toDocument(message);
        repository.save(document);
    }

    @Override
    public List<Message> getUnsentMessages(String toUserId) {
        User toUser = new User(toUserId);
        return repository.findByToUserIdAndDeliveredFalseOrderByTimestampDesc(toUserId)
                .stream()
                .map(messageDocument -> {
                    User fromUser = new User(messageDocument.getFromUserId());
                    return new Message(fromUser, toUser, messageDocument.getContent());
                }).toList();
    }
}
