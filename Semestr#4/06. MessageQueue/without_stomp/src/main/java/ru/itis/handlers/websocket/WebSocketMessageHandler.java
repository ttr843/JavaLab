package ru.itis.handlers.websocket;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.handler.TextWebSocketHandler;
import ru.itis.models.Consumer;
import ru.itis.models.JlmqConsumer;
import ru.itis.dto.MessageDto;
import ru.itis.models.MyQueue;
import ru.itis.models.Task;
import ru.itis.services.JlmqConsumerService;
import ru.itis.services.MyQueueService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@Component
@EnableWebSocket
public class WebSocketMessageHandler extends TextWebSocketHandler {

    @Autowired
    private ObjectMapper objectMapper;

    private Map<String, WebSocketSession> sessions = new HashMap<>();

    @Autowired
    private JlmqConsumerService jlmqConsumerService;

    @Autowired
    private MyQueueService myQueueService;

    @Override
    public void afterConnectionEstablished(WebSocketSession session){
        sessions.put(session.getId(), session);
    }

    @Override
    public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) {
        String messageText = (String) message.getPayload();
        MessageDto messageDto = null;
        try {
            messageDto = objectMapper.readValue(messageText, MessageDto.class);
            MyQueue myQueue = MyQueue.builder()
                    .queueName(messageDto.getQueueName())
                    .build();
            if (messageDto.getCommand().equals("subscribe")) {
                    Consumer consumer = Consumer.builder()
                            .myQueue(myQueue)
                            .socketSession(session)
                            .build();
                    jlmqConsumerService.subscribe(consumer);
            } else {
                if (!myQueueService.isExist(myQueue)) {
                    myQueueService.add(myQueue);
                }
                Task task = Task.builder()
                        .myQueue(myQueue)
                        .body(messageDto.getBody())
                        .command(messageDto.getCommand())
                        .id(UUID.randomUUID().toString())
                        .build();
                myQueue.getTasks().add(task);
                        //queueTaskService.addTask(task);
                }
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }


}