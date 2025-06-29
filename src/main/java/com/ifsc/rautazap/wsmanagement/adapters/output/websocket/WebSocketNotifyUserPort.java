package com.ifsc.rautazap.wsmanagement.adapters.output.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifsc.rautazap.wsmanagement.ports.output.NotifyUserPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class WebSocketNotifyUserPort implements NotifyUserPort {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebSocketNotifyUserPort(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Override
    public void notifyUser(String userId, String message) {
        try {
            String json = new ObjectMapper().writeValueAsString(new WebSocketResponseMessage(message));
            messagingTemplate.convertAndSendToUser(userId, "/topic/messages", json);
        } catch (JsonProcessingException e) {
            log.error("Error serializing message to JSON", e);
            //Should add message to a dead letter queue or log it for further investigation
        }
    }

    private record WebSocketResponseMessage(String message) {
    }
}
