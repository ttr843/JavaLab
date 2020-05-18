package ru.itis.messagequeue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itis.messagequeue.models.Message;
import ru.itis.messagequeue.models.Status;
import ru.itis.messagequeue.models.jpa.MessageEntity;
import ru.itis.messagequeue.models.jpa.MyQueue;
import ru.itis.messagequeue.repositories.MessageRepository;
import ru.itis.messagequeue.repositories.QueueRepository;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private MessageRepository messageRepository;

    @Autowired
    private QueueRepository queueRepository;

    @Override
    public void save(Message message, String queueName) {
        Optional<MyQueue> queue = queueRepository.findByName(queueName);
        if (queue.isPresent()) {
            MessageEntity msg = MessageEntity.builder()
                    .id(message.getMessageId())
                    .status(Status.NOT_ACCEPTED)
                    .body(message.getBody().toString())
                    .queue(queue.get())
                    .date(new Timestamp(System.currentTimeMillis()))
                    .build();
            messageRepository.save(msg);
        }

    }

    @Override
    public void updateStatus(String id, Status status) {
        Optional<MessageEntity> messageOptional = messageRepository.find(id);
        if (messageOptional.isPresent()) {
            MessageEntity message = messageOptional.get();
            message.setStatus(status);
            messageRepository.update(message);
        }
    }

    @Override
    @Transactional
    public void deleteMessage(String messageId) {
        messageRepository.delete(messageId);
    }


}
