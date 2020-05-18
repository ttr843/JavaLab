package ru.itis.configs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.itis.handlers.handshake.AuthHandshakeHandler;
import ru.itis.handlers.websocket.WebSocketMessageHandler;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

    @Autowired
    private WebSocketMessageHandler messageHandler;

    @Autowired
    private AuthHandshakeHandler authHandshakeHandler;

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry.addHandler(messageHandler, "/chat")
                .setHandshakeHandler(authHandshakeHandler)
                .withSockJS();
    }
}