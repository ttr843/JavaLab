package ru.itis.messagequeue.services;

import ru.itis.messagequeue.models.Message;
import ru.itis.messagequeue.models.Status;

public interface MessageService {
    void save(Message message, String queueName);
    void updateStatus(String id, Status status);
    void deleteMessage(String messageId);
}
