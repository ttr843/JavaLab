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

    private String login, password;

    public LoginPayload() {
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(new LoginPayload(login, password));
    }

}
