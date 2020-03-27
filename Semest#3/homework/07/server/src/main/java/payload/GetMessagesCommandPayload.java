package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.GetMessagesCommand;

public class GetMessagesCommandPayload implements PayLoad {

    public GetMessagesCommand getGetMessagesCommand() {
        return getMessagesCommand;
    }

    private GetMessagesCommand getMessagesCommand;

    public GetMessagesCommandPayload(GetMessagesCommand getMessagesCommand) {
        this.getMessagesCommand = getMessagesCommand;
    }

    public GetMessagesCommandPayload() {
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(getMessagesCommand);
    }
}
