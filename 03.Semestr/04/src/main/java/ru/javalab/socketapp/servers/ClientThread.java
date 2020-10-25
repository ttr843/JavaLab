package ru.javalab.socketapp.servers;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javalab.socketapp.database.DAO.MessageDAO;
import ru.javalab.socketapp.database.DAO.UserDAO;
import ru.javalab.socketapp.database.DBConnection;
import ru.javalab.socketapp.database.model.Message;
import ru.javalab.socketapp.database.model.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private BufferedReader reader;
    private int userId;
    private String userName;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void run() {
        try {
            reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                PrintWriter writer = new PrintWriter(clientSocket.getOutputStream(), true);
                if (!Server.getClientList().contains(this)) {
                    String name = line.split(" ")[0];
                    String password = line.split(" ")[1];
                    UserDAO userDao = new UserDAO(DBConnection.getInstance());
                    User user = userDao.find(name);
                    if (user != null ) {
                        PasswordEncoder encoder = new BCryptPasswordEncoder();
                        if (encoder.matches(password,user.getPassword())) {
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
                } else {
                    for (ClientThread client : Server.getClientList()) {
                        if (!client.equals(this)) {
                            writer = new PrintWriter(client.clientSocket.getOutputStream(), true);
                            writer.println(userName + ": " + line);
                        }
                    }
                    MessageDAO messageDao = new MessageDAO(DBConnection.getInstance());
                    messageDao.save(new Message(userId,line));
                }
            }
            reader.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
