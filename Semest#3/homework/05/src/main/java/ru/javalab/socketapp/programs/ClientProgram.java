package ru.javalab.socketapp.programs;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ru.javalab.socketapp.clients.Client;
import ru.javalab.socketapp.protocol.LoginPayload;
import ru.javalab.socketapp.protocol.MessagePayload;
import ru.javalab.socketapp.protocol.CommandPayload;
import ru.javalab.socketapp.protocol.Request;

import java.util.Scanner;

public class ClientProgram {
    public static void main(String[] args) {
        int port = 6000;
        String ip = "127.0.0.1";
        /*for (String param : args) {
            String name = param.split("=")[0];
            String arg = param.split("=")[1];
            if (name.equals("--server-ip")) {
                ip = arg;
            } else if (name.equals("--server-port")) {
                port = Integer.parseInt(arg);
            }
        }*/
        Client client = new Client();
        client.start(ip, port);
        Scanner sc = new Scanner(System.in);
        System.out.println("Login please");
        String headerOne = sc.nextLine();
        System.out.println("Name:");
        String name = sc.nextLine();
        System.out.println("Password:");
        String password = sc.nextLine();
        if (headerOne.equals("Login")) {
            Request<LoginPayload> requestLogin = new Request<>("Login", new LoginPayload(name, password));
            client.send(makeJsonFormat(requestLogin));
        }
        boolean flag = true;
        while(flag) {
            System.out.println("write header");
            String header = sc.nextLine();
            if(header.equals("Logout")){
                Request requestLogout = new Request<>("Logout",null);
                client.send(makeJsonFormat(requestLogout));
                flag = false;
            }else if(header.equals("Command")){
                System.out.println("write command");
                if(sc.nextLine().equals("get messages")) {
                    int page = sc.nextInt();
                    int size = sc.nextInt();
                    Request<CommandPayload> requestPagination = new Request<>("Command",new CommandPayload(
                            "get messages",page,size));
                    client.send(makeJsonFormat(requestPagination));
                }
            }else if(header.equals("Message")){
                System.out.println("Write message");
                Request<MessagePayload> requestMessage = new Request<>("Message", new MessagePayload(sc.nextLine()));
                client.send(makeJsonFormat(requestMessage));
            }
        }
    }

    private static String makeJsonFormat(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }
}
