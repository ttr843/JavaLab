package ru.itis.messagequeue.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.messagequeue.models.Message;
import ru.itis.messagequeue.models.MessageFactory;
import ru.itis.messagequeue.websocket.MyWebSocketClient;
import ru.itis.messagequeue.websocket.ProducerSessionHandler;

import java.util.HashMap;
import java.util.Map;

public class JlmqEmailProducerImpl extends JlmqProducer {

    public JlmqEmailProducerImpl(String queueName) {
        super(queueName);
    }

    @Override
    public void sendTask(Map<String, Object> map) {
        try {
            Map<String, Object> params = new HashMap<>();
            params.put("email", map.get("email"));
            Message message = MessageFactory.getSendMessage(params);
            producer.send(objectMapper.writeValueAsString(message), "/" + queueName);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
