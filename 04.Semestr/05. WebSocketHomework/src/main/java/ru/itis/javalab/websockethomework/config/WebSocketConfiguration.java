package ru.itis.javalab.websockethomework.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
import ru.itis.javalab.websockethomework.handler.WebSocketEventHandler;
import ru.itis.javalab.websockethomework.handler.AuthHandler;

@Configuration
public class WebSocketConfiguration implements WebSocketConfigurer {
    private final AuthHandler authHandler;
    private WebSocketEventHandler webSocketEventHandler;

    @Autowired
    public WebSocketConfiguration(WebSocketEventHandler webSocketEventHandler, AuthHandler authHandler) {
        this.webSocketEventHandler = webSocketEventHandler;
        this.authHandler = authHandler;
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry webSocketHandlerRegistry) {
        webSocketHandlerRegistry
                .addHandler(webSocketEventHandler, "/chat")
                .withSockJS()
                .setInterceptors(authHandler);
    }
}
