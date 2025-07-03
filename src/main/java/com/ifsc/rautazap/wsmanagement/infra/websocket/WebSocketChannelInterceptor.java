package com.ifsc.rautazap.wsmanagement.infra.websocket;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@Slf4j
public class WebSocketChannelInterceptor implements ChannelInterceptor {

    static final String USER_ID_HEADER = "user-id";

    @Override
    public Message<?> preSend(@NonNull Message<?> message, @NonNull MessageChannel channel) {
        final StompHeaderAccessor accessor = readHeaderAccessor(message);

        if (accessor.getCommand() == StompCommand.CONNECT) {
            String userId = readUserIdHeader(accessor);
            UsernamePasswordAuthenticationToken user = new UsernamePasswordAuthenticationToken(userId, null);
            accessor.setUser(user);
            accessor.setHeader("connection-time", LocalDateTime.now().toString());
            log.info("User with user-id {} make a WebSocket connection and generated the user {}", userId, user);
        }

        return message;

    }

    private StompHeaderAccessor readHeaderAccessor(Message<?> message) {
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        if (accessor == null) {
            throw new AuthenticationCredentialsNotFoundException("Fail to read headers.");
        }
        return accessor;
    }

    private String readUserIdHeader(StompHeaderAccessor accessor) {
        final String wsId = accessor.getFirstNativeHeader(USER_ID_HEADER);
        if (wsId == null || wsId.trim().isEmpty()) {
            throw new AuthenticationCredentialsNotFoundException("Web Socket ID Header not found");
        }
        return wsId;
    }

}
