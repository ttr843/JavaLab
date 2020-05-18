package ru.itis.repositories;

import ru.itis.models.MyQueue;

import java.util.Optional;

public interface MyQueueRepository extends CrudRepository<Long, MyQueue> {
    Optional<MyQueue> findByName(String queueName);
}
