package com.ifsc.rautazap.wsmanagement.infra.adapters.input.kafka;

import com.ifsc.rautazap.wsmanagement.domain.user.UserId;
import com.ifsc.rautazap.wsmanagement.application.ports.input.UserOnlineUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class KafkaUserOnlineListner {

    private static final String USER_ONLINE_TOPIC = "user-online";
    private static final String GROUP_ID = "user-online-group";

    private final UserOnlineUseCase userOnlineUseCase;

    @Autowired
    public KafkaUserOnlineListner(UserOnlineUseCase userOnlineUseCase) {
        this.userOnlineUseCase = userOnlineUseCase;
    }

    @KafkaListener(topics = USER_ONLINE_TOPIC, groupId = GROUP_ID)
    public void listen(UserId user) {
        userOnlineUseCase.userOnline(user);
    }
}
