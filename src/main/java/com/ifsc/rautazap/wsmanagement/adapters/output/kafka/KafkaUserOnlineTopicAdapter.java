package com.ifsc.rautazap.wsmanagement.adapters.output.kafka;

import com.ifsc.rautazap.wsmanagement.domain.user.UserDTO;
import com.ifsc.rautazap.wsmanagement.ports.output.UserOnlineTopicPort;
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
    public void publishUserOnlie(UserDTO user) {
        kafkaTemplate.send(USER_ONLINE_TOPIC, user.id(), user);
    }
}
