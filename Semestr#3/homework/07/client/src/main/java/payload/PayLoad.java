package payload;

import com.fasterxml.jackson.core.JsonProcessingException;

public interface PayLoad {
    String convertToJson() throws JsonProcessingException;
}
