package ru.itis.messagequeue;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.itis.messagequeue.components.JlmqConnector;
import ru.itis.messagequeue.components.JlmqConsumer;
import ru.itis.messagequeue.components.JlmqProducer;
import ru.itis.messagequeue.components.JlmqProducerFactory;

import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
public class MessageQueueApplication {

    public static void main(String[] args) {
        SpringApplication.run(MessageQueueApplication.class, args);

        JlmqConnector connector = new JlmqConnector();

        //consumer подписывается на конкретную очередь
        JlmqConsumer consumer = connector.consumer()
                .subscribe("documents_for_generate")
                .onReceive(message -> {
                    System.out.println(message.getBody());
                });

        JlmqProducer producer = JlmqProducerFactory.getEmailProducer();
        Map<String, Object> map = new HashMap<>();
        map.put("email", "elanskaya.lina@gmail.com");
        producer.sendTask(map);
    }
}
