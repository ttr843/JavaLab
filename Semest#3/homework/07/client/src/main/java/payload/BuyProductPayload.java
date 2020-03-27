package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class BuyProductPayload implements PayLoad {
    public BuyProductPayload() {
    }

    public int getId() {
        return id;
    }

    public BuyProductPayload(int id) {
        this.id = id;
    }

    private int id;
    public String command = "buy product";


    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
