package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.MyQueue;
import ru.itis.repositories.MyQueueRepository;

import java.util.Optional;

@Service
public class MyQueueServiceImpl implements MyQueueService {

    @Autowired
    private MyQueueRepository myQueueRepository;

    @Override
    public boolean isExist(MyQueue queue) {
        Optional<MyQueue> myQueueOptional = myQueueRepository.findByName(queue.getQueueName());
        return myQueueOptional.isPresent();
    }

    @Override
    public void add(MyQueue queue) {
        myQueueRepository.save(queue);
    }
}
