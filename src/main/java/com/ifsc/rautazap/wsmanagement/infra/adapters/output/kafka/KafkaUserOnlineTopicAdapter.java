package com.ifsc.rautazap.wsmanagement.infra.adapters.output.kafka;

import com.ifsc.rautazap.wsmanagement.domain.user.UserId;
import com.ifsc.rautazap.wsmanagement.application.ports.output.UserOnlineTopicPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaUserOnlineTopicAdapter implements UserOnlineTopicPort {

    private static final String USER_ONLINE_TOPIC = "user-online";

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Autowired
    public KafkaUserOnlineTopicAdapter(KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Override
    public void publishUserOnline(UserId user) {
        kafkaTemplate.send(USER_ONLINE_TOPIC, user.value(), user);
    }
}
