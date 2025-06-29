package com.ifsc.rautazap.wsmanagement.adapters.output.kafka;

import com.ifsc.rautazap.wsmanagement.ports.output.SendMessageTopicPort;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaSendMessageTopicAdapter implements SendMessageTopicPort {

    private static final String SEND_MESSAGE_TOPIC = "send-message";

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public KafkaSendMessageTopicAdapter(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishSendMessage(String toUser, String message) {
        kafkaTemplate.send(SEND_MESSAGE_TOPIC, toUser, message);
    }
}
