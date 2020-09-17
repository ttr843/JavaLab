package ru.javalab.socketapp.servers;


import java.io.IOException;
import java.net.ServerSocket;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static List<ClientThread> clientList = new ArrayList<>();

    public static List<ClientThread> getClientList() {
        return clientList;
    }

    public void start(int port) {
        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                new ClientThread(serverSocket.accept()).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void logout(ClientThread clientThread) {
        clientList.remove(clientThread);
    }
}