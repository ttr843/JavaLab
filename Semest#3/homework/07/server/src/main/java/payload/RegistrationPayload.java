package payload;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class RegistrationPayload implements PayLoad {
    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
    public String getRole() {
        return role;
    }

    private String login;
    private String password;
    private String role;

    public RegistrationPayload() {
    }

    public RegistrationPayload(String login, String password, String role) {
        this.login = login;
        this.password = password;
        this.role = role;
    }

    @Override
    public String convertToJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(this);
    }
}
