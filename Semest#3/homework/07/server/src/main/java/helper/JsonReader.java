package helper;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import context.Component;

import java.util.HashMap;
import java.util.Map;

public class JsonReader implements Component {
    private String req;

    public JsonReader() {
    }

    public void setReq(String req) {
        this.req = req;
    }

    public String getCommand() {
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode, jsonPayload;
        try {
            ObjectNode objectNode = mapper.readValue(req, ObjectNode.class);
            jsonPayload = objectNode.get("payLoad");
            jsonNode = objectNode.get("header");
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        String s = jsonNode.asText();
        if (s.equals("command"))
            return jsonPayload.findValue("command").asText();
        else return s;
    }

    public Map<String, String> getParameters() {
        ObjectMapper mapper = new ObjectMapper();
        ObjectNode objectNode;
        try {
            objectNode = mapper.readValue(req, ObjectNode.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
        JsonNode jsonPayload = objectNode.get("payLoad");
        try {
            return mapper.readValue(jsonPayload.toString(), HashMap.class);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

}
