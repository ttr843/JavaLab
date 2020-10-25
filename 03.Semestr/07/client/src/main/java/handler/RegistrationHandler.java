package handler;

import payload.JsonObject;
import payload.RegistrationPayload;
import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Scanner;

public class RegistrationHandler implements Handler {

    public String process() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your login");
        String login = sc.nextLine();
        System.out.println("enter the password");
        String password = sc.nextLine();
        System.out.println("enter your role");
        String role = sc.nextLine();
        String jsonLogin;
        try {
            jsonLogin = new JsonObject("registration", new RegistrationPayload(login, password, role)).convertToJson();
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
        return jsonLogin;
    }
}
