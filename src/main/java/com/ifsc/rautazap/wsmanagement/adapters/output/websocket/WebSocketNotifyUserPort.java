package com.ifsc.rautazap.wsmanagement.adapters.output.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ifsc.rautazap.wsmanagement.domain.message.Message;
import com.ifsc.rautazap.wsmanagement.ports.output.NotifyUserPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessagingException;
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
    public void notifyUser(Message.MessageData message) {
        try {
            String json = new ObjectMapper().writeValueAsString(new WebSocketResponseMessage(message.id(), message.fromUserId(), message.content()));
            messagingTemplate.convertAndSendToUser(message.toUserId(), "/topic/messages", json);
        } catch (JsonProcessingException e) {
            log.error("Error serializing message to JSON", e);
        } catch (MessagingException e) {
            log.error("Error sending message via WebSocket", e);
        }
    }

    private record WebSocketResponseMessage(String messageId, String fromUserId, String content) {
    }
}
