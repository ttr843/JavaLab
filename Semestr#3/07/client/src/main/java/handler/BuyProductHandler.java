package handler;

import payload.BuyProductPayload;
import payload.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Scanner;

public class BuyProductHandler implements Handler {

    public String process() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter product id");
        int id = sc.nextInt();
        String jsonGetMessages;
        try {
            jsonGetMessages = new JsonObject("command", new BuyProductPayload(id)).convertToJson();
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        return jsonGetMessages;
    }
}
