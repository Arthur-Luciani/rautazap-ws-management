package com.ifsc.rautazap.wsmanagement.infra.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
@Slf4j
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${broker.host}")
    private String relayHost;

    @Value("${broker.port}")
    private int relayPort;

    @Value("${broker.username}")
    private String artemisUser;

    @Value("${broker.password}")
    private String artemisPassword;

    private final WebSocketChannelInterceptor channelInterceptor;

    @Autowired
    public WebSocketConfig(WebSocketChannelInterceptor channelInterceptor) {
        this.channelInterceptor = channelInterceptor;
    }

    @Override
    public void registerStompEndpoints(org.springframework.web.socket.config.annotation.StompEndpointRegistry registry) {
        registry.addEndpoint("/ws-register")
                .withSockJS();
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        log.info("Configuring message broker with relay host: {}, port: {}", relayHost, relayPort);
        registry.enableStompBrokerRelay("/queue", "/topic")
                .setRelayHost(relayHost)
                .setRelayPort(relayPort)
                .setClientLogin(artemisUser)
                .setClientPasscode(artemisPassword)
                .setSystemLogin(artemisUser)
                .setSystemPasscode(artemisPassword)
                .setUserDestinationBroadcast("/topic/unresolved-user")
                .setUserRegistryBroadcast("/topic/log-user-registry");

        registry.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(channelInterceptor);
    }
}
