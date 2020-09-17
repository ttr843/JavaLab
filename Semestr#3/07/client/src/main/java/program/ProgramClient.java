package program;

import client.SocketClient;

import java.util.Scanner;

public class ProgramClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int port = 6000;
        String ip = "127.0.0.1";
        for (String param : args) {
            String name = param.split("=")[0];
            String arg = param.split("=")[1];
            if (name.equals("--server-ip")) {
                ip = arg;
            } else if (name.equals("--server-port")) {
                port = Integer.parseInt(arg);
            }
        }
        SocketClient client = new SocketClient();
        client.startConnection(ip, port);
        while (true) {
            String message = scanner.nextLine();
            client.sendJson(message);
        }
    }
}
