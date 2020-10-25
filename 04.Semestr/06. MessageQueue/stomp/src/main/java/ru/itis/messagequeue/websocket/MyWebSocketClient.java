package ru.itis.messagequeue.websocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.converter.StringMessageConverter;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;
import org.springframework.web.socket.messaging.WebSocketStompClient;
import org.springframework.web.socket.sockjs.client.SockJsClient;
import org.springframework.web.socket.sockjs.client.Transport;
import org.springframework.web.socket.sockjs.client.WebSocketTransport;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

@Slf4j
public class MyWebSocketClient {

    private StompSession session;
    private WebSocketStompClient stompClient;
    private boolean isConnected = false;

    public MyWebSocketClient() {
        List<Transport> transports = new ArrayList<>();
        transports.add(new WebSocketTransport(new StandardWebSocketClient()));
        WebSocketClient client = new SockJsClient(transports);
        stompClient = new WebSocketStompClient(client);
        stompClient.setMessageConverter(new StringMessageConverter());
    }

    public void connect(String url, StompSessionHandlerAdapter handler) {
        try {
            session = stompClient.connect("http://localhost:8080" + url, handler).get();
        } catch (InterruptedException | ExecutionException e) {
            throw new IllegalArgumentException(e);
        }
        isConnected = true;
    }

    public void subscribe(String queueName, StompSessionHandlerAdapter handlerAdapter) {
        session.subscribe("/topic/queue/" + queueName, handlerAdapter);
        log.info("Consumer subscribed to /topic/queue/" + queueName);
    }

    public void send(String message, String path) {
        if (isConnected) {
            session.send("/app" + path, message);
        } else {
            log.warn("Client isn't connected to websocket");
        }
    }
}
