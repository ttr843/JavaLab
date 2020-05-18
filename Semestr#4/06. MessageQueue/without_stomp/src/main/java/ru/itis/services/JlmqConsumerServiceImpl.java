package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.Consumer;
import ru.itis.models.JlmqConsumer;
import ru.itis.repositories.JlmqConsumerRepository;

@Service
public class JlmqConsumerServiceImpl implements JlmqConsumerService {

    @Autowired
    private JlmqConsumerRepository jlmqConsumerRepository;

    @Override
    public void subscribe(Consumer consumer) {
        JlmqConsumer jlmqConsumer = JlmqConsumer.builder()
                .myQueue(consumer.getMyQueue())
                .build();
        jlmqConsumerRepository.save(jlmqConsumer);
    }
}
