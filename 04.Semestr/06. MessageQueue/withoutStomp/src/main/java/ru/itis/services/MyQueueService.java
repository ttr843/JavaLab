package ru.itis.services;

import ru.itis.models.MyQueue;

public interface MyQueueService {
    boolean isExist(MyQueue myQueue);
    void add(MyQueue queue);
}
