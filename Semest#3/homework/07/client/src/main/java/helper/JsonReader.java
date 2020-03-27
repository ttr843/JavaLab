package helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import model.Message;
import model.Product;

import java.util.Date;

public class JsonReader {

    public void parse(String json) {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode, jsonPayload;
        ObjectNode objectNode;
        try {
             objectNode = mapper.readValue(json, ObjectNode.class);
            /*jsonNode = objectNode.get("header");
            jsonPayload = objectNode.get("payLoad");*/
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        String command = json.substring(2, 15);
        if (command.contains("messages")) {
            try {
                Message[] messages = mapper.readValue(objectNode.get("messages").toString(), Message[].class);
                for (Message message : messages) {
                    System.out.println(message.getSender().getLogin() + ": " + message.getText() +
                            "||| sent at " + message.getTimesent().toString());
                }
            } catch (JsonProcessingException e) {
                throw new IllegalStateException(e);
            }
        } else if (command.contains("message")) {
            JsonNode message = objectNode.get("message");
            String response = message.get("sender").get("login").asText() + ": " + message.get("text").asText() +
                    "||| sent at " + new Date(Long.parseLong(message.get("timesent").asText()));
            System.out.println(response);
        } else if (command.contains("products")) {
            try {
                Product[] products = mapper.readValue(objectNode.get("products").toString(), Product[].class);
                for (Product product : products) {
                    System.out.println(product.getId() + ". " + product.getName() + " price: " + product.getPrice());
                }
            } catch (JsonProcessingException e) {
                throw new IllegalStateException(e);
            }

        }
    }
}
