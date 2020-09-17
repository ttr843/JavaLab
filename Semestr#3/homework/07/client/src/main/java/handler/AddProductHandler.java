package handler;

import payload.AddProductPayload;
import payload.JsonObject;
import com.fasterxml.jackson.core.JsonProcessingException;
import model.Product;

import java.util.Scanner;

public class AddProductHandler implements Handler{
    private String token;

    public AddProductHandler(String token) {
        this.token = token;
    }

    public String process() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter product name");
        String name = sc.nextLine();
        System.out.println("enter product price");
        int size = sc.nextInt();
        String jsonGetMessages;
        try {
            jsonGetMessages = new JsonObject("command", new AddProductPayload(new Product(name, size), token)).convertToJson();
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        return jsonGetMessages;
    }
}
