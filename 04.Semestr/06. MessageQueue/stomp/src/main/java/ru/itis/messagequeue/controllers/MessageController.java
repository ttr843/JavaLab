package ru.itis.messagequeue.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.stereotype.Controller;
import ru.itis.messagequeue.components.MessageQueue;

@Controller
@Slf4j
public class MessageController {

    @Autowired
    private MessageQueue messageQueue;

    @MessageMapping("/{queueName}")
    public void receiveMessage(Message<String> message, @DestinationVariable String queueName){
        log.info("Received new task for : " + queueName);
        messageQueue.addMessageToQueue(message.getPayload(), queueName);
    }

    @MessageMapping("/accept/{queueName}/{messageId}")
    public void acceptMessage(@DestinationVariable String queueName, @DestinationVariable String messageId){
        messageQueue.acceptMessage(queueName, messageId);
        log.info("Accepted message " + messageId);
    }

    @MessageMapping("/confirm/{queueName}/{messageId}")
    public void confirmMessage(@DestinationVariable String queueName, @DestinationVariable String messageId){
        messageQueue.confirmMessage(queueName, messageId);
        log.info("Confirmed message " + messageId);
    }

}
