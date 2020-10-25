package client;

import handler.LoginHandler;
import helper.CommandDispatcher;
import helper.JsonReader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class SocketClient {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private String token;

    public BufferedReader getIn() {
        return in;
    }

    public void startConnection(String ip, int port) {
        try {
            clientSocket = new Socket(ip, port);
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            new Thread(receiverMessagesTask).start();
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public void sendMessage(String message) {
        out.println(message);
    }

    public void sendJson(String message) {
        String json = new CommandDispatcher(token).process(message);
        out.println(json);
    }

    private Runnable receiverMessagesTask = new Runnable() {
        @Override
        public void run() {
            while (true) {
                try {
                    String response = in.readLine();
                    if (response.equals("Login->") || response.equals("password is incorrect")) {
                        System.out.println("Login->");
                        new LoginHandler().process();
                    } else if (response.contains("nice")){
                        System.out.println("You're in chat!");
                        token = response.split(";")[1];
                    }
                    else if (response.equals("done!")){
                        System.out.println(response);
                    }
                    else {
                        new JsonReader().parse(response);
                    }
                } catch (IOException e) {
                    throw new IllegalStateException(e);
                }
            }
        }
    };

    public void stopConnection() {
        try {
            in.close();
            out.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
}
