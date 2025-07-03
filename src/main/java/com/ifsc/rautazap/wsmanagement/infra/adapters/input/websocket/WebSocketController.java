package com.ifsc.rautazap.wsmanagement.infra.adapters.input.websocket;

import com.ifsc.rautazap.wsmanagement.domain.message.SendMessageCommand;
import com.ifsc.rautazap.wsmanagement.application.ports.input.SendMessageUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import java.security.Principal;

@Controller
@Slf4j
public class WebSocketController {

    private final SendMessageUseCase sendMessageUseCase;

    @Autowired
    public WebSocketController(SendMessageUseCase sendMessageUseCase) {
        this.sendMessageUseCase = sendMessageUseCase;
    }

    @MessageMapping("message/{toUser}")
    public boolean sendMessage(Principal principal, @DestinationVariable String toUser, @RequestBody WebSocketRequestMessage message) {
        sendMessageUseCase.sendMessage(new SendMessageCommand(principal.getName(), toUser, message.messageContent));
        return true;
    }

    public record WebSocketRequestMessage(String messageContent) {
    }
}
