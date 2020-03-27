package handler;

import payload.JsonObject;
import payload.LoginPayload;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Scanner;


public class LoginHandler implements Handler {

    public String process() {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter your login");
        String login = sc.nextLine();
        System.out.println("enter the password");
        String password = sc.nextLine();
        String jsonLogin;
        try {
            jsonLogin = new JsonObject("login", new LoginPayload(login, password)).convertToJson();
        } catch (JsonProcessingException e) {
            throw new IllegalArgumentException(e);
        }
       return jsonLogin;
    }

}
