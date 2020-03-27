package handler;

import payload.DeleteProductPayload;
import payload.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Scanner;

public class DeleteProductHandler {
    private String token;

    public DeleteProductHandler(String token) {
        this.token = token;
    }

    public String process() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter product id");
        int id = sc.nextInt();
        String jsonGetMessages;
        try {
            jsonGetMessages = new JsonObject("command", new DeleteProductPayload(id, token)).convertToJson();
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        return jsonGetMessages;
    }
}
