package ru.itis.messagequeue.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.messagequeue.components.MessageQueue;
import ru.itis.messagequeue.models.jpa.MyQueue;

@RestController
public class QueueController {

    @Autowired
    private MessageQueue messageQueue;

    @PostMapping(value = "/createQueue")
    public ResponseEntity<String> createQueue(@RequestBody MyQueue myQueue) {
        messageQueue.createQueue(myQueue.getQueueName());
        return ResponseEntity.ok("The queue \"" + myQueue.getQueueName() + "\" successfully created");
    }
}
