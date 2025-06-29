package com.ifsc.rautazap.wsmanagement.adapters.input.websocket;

import com.ifsc.rautazap.wsmanagement.ports.input.SendMessageUseCase;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@Slf4j
public class WebSocketController {

    private final SendMessageUseCase sendMessageUseCase;

    @Autowired
    public WebSocketController(SendMessageUseCase sendMessageUseCase) {
        this.sendMessageUseCase = sendMessageUseCase;
    }

    @MessageMapping("message/{toUser}")
    public boolean sendMessage(@DestinationVariable String toUser, @RequestBody WebSocketRequestMessage message) {
        sendMessageUseCase.sendMessage(toUser, message.getMessageContent());
        return true;
    }

    @Getter
    @Setter
    public static class WebSocketRequestMessage {
        private String messageContent;
    }
}
