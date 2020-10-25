package ru.itis.messagequeue.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.messagequeue.models.jpa.MyQueue;
import ru.itis.messagequeue.repositories.QueueRepository;

import java.util.Optional;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private QueueRepository queueRepository;

    @Override
    public boolean isExist(String queueName) {
        Optional<MyQueue> queueOptional = queueRepository.findByName(queueName);
        return queueOptional.isPresent();
    }

    @Override
    public void add(String queueName) {
        queueRepository.save(MyQueue.builder()
                .queueName(queueName)
                .build());
    }
}
