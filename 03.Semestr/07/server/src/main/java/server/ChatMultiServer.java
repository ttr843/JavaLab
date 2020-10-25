package server;


import Dto.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import context.Component;
import protocol.Request;
import protocol.RequestsHandler;
import protocol.Response;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ChatMultiServer implements Component {

    private List<ClientHandler> clients;
    public ChatMultiServer() {
        clients = new CopyOnWriteArrayList<>();
    }
    private RequestsHandler requestsHandler;
    private Request request;

    public void start(int port) {
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
        while (true) {
            try {
                new ClientHandler(serverSocket.accept()).start();
            } catch (IOException e) {
                throw new IllegalStateException(e);
            }
        }
    }

    private class ClientHandler extends Thread {

        private Socket clientSocket;
        private BufferedReader in;
        private PrintWriter out;


        ClientHandler(Socket socket) {
            this.clientSocket = socket;
            clients.add(this);
            System.out.println("New client");
        }

        public void run() {
            try {
                in = new BufferedReader(
                        new InputStreamReader(clientSocket.getInputStream()));
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                ObjectMapper mapper = new ObjectMapper();
                boolean flag = true;
                String inputLine;
                while (flag) {
                    inputLine = in.readLine();
                    request.setRequest(inputLine);
                    Response response = requestsHandler.handleRequest(request);
                    if(response.getData() instanceof UserJwtDto) {
                        out.println("nice;" + UserJwtDto.getJwt());
                    } else if (response.getData() instanceof ErrorDto) {
                        out.println("login->");
                    } else if (response.getData() instanceof ForAllDto) {
                        for (ClientHandler clientHandler : clients) {
                            PrintWriter pw = new PrintWriter(clientHandler.clientSocket.getOutputStream(), true);
                            pw.println(mapper.writeValueAsString(response.getData()));
                        }
                    } else if (response.getData() instanceof ForOneDto){
                        out.println(mapper.writeValueAsString(response.getData()));
                    } else {
                        out.println("done!");
                    }
                }
                in.close();
                clientSocket.close();
            } catch (Exception e) {
                throw new IllegalStateException(e);
            }
        }
    }
}
