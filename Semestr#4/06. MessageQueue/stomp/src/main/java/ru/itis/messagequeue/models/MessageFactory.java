package ru.itis.messagequeue.models;

import java.util.Map;

public class MessageFactory {

    public static Message getAcceptMessage(String messageId){
        return Message.builder().messageId(messageId).command("accept").build();
    }

    public static Message getCompletedMessage(String messageId){
        return Message.builder().messageId(messageId).command("completed").build();
    }

    public static Message getSendMessage(Map<String, Object> body){
        return Message.builder().command("send").body(body).build();
    }

}
