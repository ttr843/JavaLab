package ru.itis.messagequeue.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.messagequeue.models.MessageFactory;
import ru.itis.messagequeue.websocket.ConsumerSessionHandler;
import ru.itis.messagequeue.websocket.MessageHandler;
import ru.itis.messagequeue.websocket.MyWebSocketClient;

public class JlmqConsumer {

    private ObjectMapper objectMapper;
    private MyWebSocketClient consumer;
    private String queueName;
    private MessageHandler handler;
    private ConsumerSessionHandler sessionHandler;

    public JlmqConsumer() {
        consumer = new MyWebSocketClient();
        this.sessionHandler = new ConsumerSessionHandler(message -> {
            accept(message.getMessageId());
            handler.onReceive(message);
            complete(message.getMessageId());
        });
        consumer.connect("/websocket", sessionHandler);
        this.objectMapper = new ObjectMapper();
    }

    public JlmqConsumer onReceive(MessageHandler handler){
        this.handler = handler;
        return this;
    }

    //consumer подписывается на очередь
    public JlmqConsumer subscribe(String queueName) {
        this.queueName = queueName;
        consumer.subscribe(queueName, sessionHandler);
        return this;
    }


    private void accept(String messageId) {
        try {
            String message = objectMapper.writeValueAsString(MessageFactory.getAcceptMessage(messageId));
            consumer.send(message, "/accept/" + queueName + "/" + messageId);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    private void complete(String messageId) {
        try {
            String message = objectMapper.writeValueAsString(MessageFactory.getAcceptMessage(messageId));
            consumer.send(message, "/confirm/" + queueName + "/" + messageId);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

}
