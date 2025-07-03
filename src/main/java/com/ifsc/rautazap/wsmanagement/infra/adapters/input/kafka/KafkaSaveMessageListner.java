package com.ifsc.rautazap.wsmanagement.infra.adapters.input.kafka;

import com.ifsc.rautazap.wsmanagement.domain.message.Message;
import com.ifsc.rautazap.wsmanagement.application.ports.input.SaveMessageUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaSaveMessageListner {

    private static final String SAVE_MESSAGE_TOPIC = "save-message";
    private static final String GROUP_ID = "message-save-group";

    private final SaveMessageUseCase saveMessageUseCase;

    @Autowired
    public KafkaSaveMessageListner(SaveMessageUseCase saveMessageUseCase) {
        this.saveMessageUseCase = saveMessageUseCase;
    }

    @KafkaListener(topics = SAVE_MESSAGE_TOPIC, groupId = GROUP_ID)
    public void listen(Message.MessageData message) {
        saveMessageUseCase.saveMessage(message);
    }
}

