package ru.itis.messagequeue.services;

public interface QueueService {
    boolean isExist(String queueName);
    void add(String queueName);
}
