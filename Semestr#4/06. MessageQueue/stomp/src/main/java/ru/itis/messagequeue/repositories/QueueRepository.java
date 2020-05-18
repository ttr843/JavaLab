package ru.itis.messagequeue.repositories;

import ru.itis.messagequeue.models.jpa.MyQueue;

import java.util.Optional;

public interface QueueRepository extends CrudRepository<Long, MyQueue> {
    Optional<MyQueue> findByName(String queueName);
}
