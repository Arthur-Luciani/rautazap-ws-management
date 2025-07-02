package com.ifsc.rautazap.wsmanagement.adapters.output.kafka;

import com.ifsc.rautazap.wsmanagement.domain.message.MessageDTO;
import com.ifsc.rautazap.wsmanagement.ports.output.SendMessageTopicPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSaveMessageTopicAdapter implements SendMessageTopicPort {

    private static final String SAVE_MESSAGE_TOPIC = "save-message";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaSaveMessageTopicAdapter(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishSaveMessage(MessageDTO message) {
        kafkaTemplate.send(SAVE_MESSAGE_TOPIC, message.toUserId(), message);
    }
}
