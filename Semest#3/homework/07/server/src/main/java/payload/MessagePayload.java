package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;
import model.Message;

@Getter
public class MessagePayload implements PayLoad {

    public MessagePayload(Message message) {
        this.message = message;
    }
    private Message message;

    public MessagePayload() {
    }
    public String getToken() {
        return token;
    }

    private String token;

    public MessagePayload(Message message, String token) {
        this.message = message;
        this.token = token;
    }
    public Message getMessage() {
        return message;
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(message);
    }

}
