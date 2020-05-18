package ru.itis.messagequeue.repositories;

import ru.itis.messagequeue.models.jpa.MessageEntity;
import ru.itis.messagequeue.models.jpa.MyQueue;

import java.util.List;

public interface MessageRepository extends CrudRepository<String, MessageEntity> {
    void update(MessageEntity message);
    List<MessageEntity> findAllByQueue(MyQueue queue);
}
