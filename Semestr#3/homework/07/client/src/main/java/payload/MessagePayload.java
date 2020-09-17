package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class MessagePayload implements PayLoad {

    public MessagePayload(String message) {
        this.message = message;
    }
    private String message;

    public String getToken() {
        return token;
    }

    private String token;

    public MessagePayload(String message, String token) {
        this.message = message;
        this.token = token;
    }

    public MessagePayload() {
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

}
