package com.ifsc.rautazap.wsmanagement.infra.adapters.input.websocket;

import com.ifsc.rautazap.wsmanagement.domain.user.UserId;
import com.ifsc.rautazap.wsmanagement.application.ports.input.UserPresenceUseCase;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.security.Principal;

@Component
@Slf4j
public class WebSocketEventListner {

    private final UserPresenceUseCase userPresenceUseCase;

    public WebSocketEventListner(UserPresenceUseCase userPresenceUseCase) {
        this.userPresenceUseCase = userPresenceUseCase;
    }

    @EventListener
    public void handleWebSocketConnectListener(SessionConnectedEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Principal userPrincipal = headerAccessor.getUser();

        if (userPrincipal != null && userPrincipal.getName() != null) {
            log.info("Adapter: Connection event received for user {}", userPrincipal.getName());
            userPresenceUseCase.onUserConnected(new UserId(userPrincipal.getName()));
        }
    }

    @EventListener
    public void handleWebSocketDisconnectListener(SessionDisconnectEvent event) {
        StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        Principal userPrincipal = headerAccessor.getUser();

        if (userPrincipal != null && userPrincipal.getName() != null) {
            log.info("Adapter: Disconnect event received for user {}", userPrincipal.getName());
            userPresenceUseCase.onUserDisconnected(new UserId(userPrincipal.getName()));
        }
    }

}
