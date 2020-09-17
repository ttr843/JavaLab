package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DeleteProductPayload implements PayLoad{
    private int id;
    private String token;

    public DeleteProductPayload(int id, String token) {
        this.id = id;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public String command = "delete product";

    public int getId() {
        return id;
    }

    public DeleteProductPayload(int id) {
        this.id = id;
    }

    public DeleteProductPayload() {
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
