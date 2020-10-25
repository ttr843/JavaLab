package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Message;

import java.util.List;

public class MessagesPayload implements PayLoad {

    public List<Message> getMessages() {
        return messages;
    }

    private List<Message> messages;

    public MessagesPayload() {
    }

    public  MessagesPayload(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
