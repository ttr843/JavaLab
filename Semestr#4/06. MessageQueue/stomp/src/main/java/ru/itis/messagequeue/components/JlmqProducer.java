package ru.itis.messagequeue.components;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.itis.messagequeue.websocket.MyWebSocketClient;
import ru.itis.messagequeue.websocket.ProducerSessionHandler;

import java.util.Map;

public abstract class JlmqProducer {

    public String queueName;
    public ObjectMapper objectMapper;
    public MyWebSocketClient producer;

    public JlmqProducer(String queueName) {
        this.queueName = queueName;
        producer = new MyWebSocketClient();
        producer.connect("/websocket", new ProducerSessionHandler());
        this.objectMapper = new ObjectMapper();
    }

    public abstract void sendTask(Map<String, Object> map);
}
