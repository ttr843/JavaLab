package handler;

import payload.JsonObject;
import payload.MessagePayload;
import com.fasterxml.jackson.core.JsonProcessingException;


public class MessageHandler {
    private String token;

    public MessageHandler(String token) {
        this.token = token;
    }

    public String process(String message) {
        String jsonMessage;
        try {
            jsonMessage = new JsonObject("message", new MessagePayload(message, token)).convertToJson();
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        return jsonMessage;
    }

}
