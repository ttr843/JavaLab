package ru.javalab.socketapp.servers;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javalab.socketapp.database.DAO.MessageDAO;
import ru.javalab.socketapp.database.DAO.UserDAO;
import ru.javalab.socketapp.database.DBConnection;
import ru.javalab.socketapp.database.model.Message;
import ru.javalab.socketapp.database.model.User;
import ru.javalab.socketapp.protocol.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private BufferedReader reader;
    private int userId;
    private String userName;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    private static String makeJsonFormat(Object o) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.writeValueAsString(o);
        } catch (JsonProcessingException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                ObjectMapper objectMapper = new ObjectMapper();
                Request request = objectMapper.readValue(line, Request.class);
                if (request.getHeader().equals("Login")) {
                    Request<LoginPayload> loginRequest = objectMapper.readValue(line, new TypeReference<Request<LoginPayload>>() {
                    });
                    String name = loginRequest.getPayload().getName();
                    String password = loginRequest.getPayload().getPassword();
                    UserDAO userDao = new UserDAO(DBConnection.getInstance());
                    User user = userDao.find(name);
                    if (user != null) {
                        PasswordEncoder encoder = new BCryptPasswordEncoder();
                        if (encoder.matches(password, user.getPassword())) {
                            userId = user.getId();
                            userName = user.getUsername();
                            Server.getClientList().add(this);
                            writer.println("You entered");
                        } else {
                            System.out.println("Incorrect Password");
                        }
                    } else {
                        PasswordEncoder encoder = new BCryptPasswordEncoder();
                        userDao.save(new User(0, name, encoder.encode(password)));
                        userId = userDao.find(name).getId();
                        userName = userDao.find(name).getUsername();
                        Server.getClientList().add(this);
                        writer.println("You entered");
                    }
                } else if (request.getHeader().equals("Logout")) {
                    Server.logout(this);
                } else if (request.getHeader().equals("Message")) {
                    Request<MessagePayload> messageRequest = objectMapper.readValue(line,
                            new TypeReference<Request<MessagePayload>>() {
                    });
                    for (ClientThread client : Server.getClientList()) {
                        if (!client.equals(this)) {
                            writer = new PrintWriter(client.clientSocket.getOutputStream(), true);
                            writer.println(userName + ": " + messageRequest.getPayload().getMessage());
                        }
                    }
                    MessageDAO messageDAO = new MessageDAO(DBConnection.getInstance());
                    messageDAO.save(new Message(userId, messageRequest.getPayload().getMessage()));
                } else if (request.getHeader().equals("Command")) {
                    Request<CommandPayload> commandRequest = objectMapper.readValue(line, new TypeReference<Request<CommandPayload>>() {
                    });
                    if (commandRequest.getPayload().getCommand().equals("get messages")) {
                        MessageDAO messageDAO = new MessageDAO(DBConnection.getInstance());
                        ArrayList<Message> messages = messageDAO.find(commandRequest.getPayload().getPage(),
                                commandRequest.getPayload().getSize());
                        if (!messages.isEmpty()) {
                            writer.println(makeJsonFormat(new PaginationResponse(messages)));
                        } else {
                            writer.println("empty");
                        }
                    }
                }
            }
            reader.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
