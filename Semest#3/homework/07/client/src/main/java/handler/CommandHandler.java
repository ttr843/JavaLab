package handler;

import payload.GetMessagesCommandPayload;
import payload.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Scanner;

public class CommandHandler implements Handler{
    private String token;

    public CommandHandler(String token) {
        this.token = token;
    }

    public String process() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter page number");
        int page = sc.nextInt();
        System.out.println("enter number of messages");
        int size = sc.nextInt();
        String jsonGetMessages;
        try {
            jsonGetMessages = new JsonObject("command", new GetMessagesCommandPayload(page, size, token)).convertToJson();
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        return jsonGetMessages;
    }

}
