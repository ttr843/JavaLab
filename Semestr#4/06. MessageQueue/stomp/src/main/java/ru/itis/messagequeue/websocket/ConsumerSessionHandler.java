package ru.itis.messagequeue.websocket;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.simp.stomp.StompHeaders;
import org.springframework.messaging.simp.stomp.StompSession;
import org.springframework.messaging.simp.stomp.StompSessionHandlerAdapter;
import ru.itis.messagequeue.models.Message;

@Slf4j
public class ConsumerSessionHandler extends StompSessionHandlerAdapter {

    private ObjectMapper objectMapper;
    private MessageHandler handler;

    public ConsumerSessionHandler(MessageHandler handler) {
        this.objectMapper = new ObjectMapper();
        this.handler = handler;
    }

    @Override
    public void afterConnected(StompSession session, StompHeaders connectedHeaders) {
        log.info("Consumer is connected to Message Queue");
    }

    @Override
    public void handleFrame(StompHeaders headers, Object payload) {
        log.info("New message for consumer:");
        log.info((String) payload);
        try {
            handler.onReceive(objectMapper.readValue((String) payload, Message.class));
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
