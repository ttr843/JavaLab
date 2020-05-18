package ru.itis.services;

import ru.itis.models.Consumer;
import ru.itis.models.JlmqConsumer;

public interface JlmqConsumerService {
    void subscribe(Consumer consumer);
}
