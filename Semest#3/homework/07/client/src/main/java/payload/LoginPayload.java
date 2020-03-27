package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Getter;

@Getter
public class LoginPayload implements PayLoad {
    public LoginPayload(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public LoginPayload() {
    }

    private String login, password;

    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }

}
