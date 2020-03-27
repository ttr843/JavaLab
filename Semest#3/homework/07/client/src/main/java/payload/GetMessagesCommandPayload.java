package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GetMessagesCommandPayload implements PayLoad {


    public GetMessagesCommandPayload(int page, int size, String token) {
        this.page = page;
        this.size = size;
        this.token = token;
    }

    public String command = "get messages";
    private String token;

    public String getToken() {
        return token;
    }

    private int page, size;

    public int getPage() {
        return page;
    }

    public int getSize() {
        return size;
    }

    public GetMessagesCommandPayload() {
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
