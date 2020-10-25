package ru.itis.messagequeue.components;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import ru.itis.messagequeue.models.Message;
import ru.itis.messagequeue.models.Status;
import ru.itis.messagequeue.services.MessageService;
import ru.itis.messagequeue.services.QueueService;
import java.util.*;

@Component
@Slf4j
public class MessageQueue {

    @Autowired
    private SimpMessagingTemplate template;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private MessageService messageService;

    @Autowired
    private QueueService queueService;

    public void addMessageToQueue(String message, String queueName){
        createQueue(queueName);
        if(queueService.isExist(queueName)) {
            Message msg;
            try {
                msg = objectMapper.readValue(message, Message.class);
            } catch (JsonProcessingException e) {
                throw new IllegalArgumentException(e);
            }
            msg.setCommand("receive");
            msg.setMessageId(UUID.randomUUID().toString());
            messageService.save(msg, queueName);
            notifySubscriberOfQueue(msg, queueName);
        } else {
            log.warn("Queue " + queueName + " is not created yet");
        }
    }

    private void notifySubscriberOfQueue(Message message, String queueName) {
        try {
            String msg = objectMapper.writeValueAsString(message);
            template.convertAndSend("/topic/queue/" + queueName, msg);
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public void acceptMessage(String queueName, String messageId){
        //принимаем сообщение
        messageService.updateStatus(messageId, Status.ACCEPTED);
    }

    public void confirmMessage(String queueName, String messageId){
        //удаляем сообщение
        messageService.deleteMessage(messageId);
    }


    public void createQueue(String queueName) {
        queueService.add(queueName);
        log.info("Created new queue: " + queueName);
    }


}
