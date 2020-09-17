package ru.javalab.socketsshop.program;

import ru.javalab.socketsshop.Helpers.JSON;
import ru.javalab.socketsshop.JSONProtocol.*;
import ru.javalab.socketsshop.client.Client;
import ru.javalab.socketsshop.server.ClientThread;

import java.io.File;
import java.io.FileNotFoundException;
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
           client.send(JSON.makeJsonFormat(requestLogin));
        }
        boolean flag = true;
        while(flag) {
            String token = ClientThread.USER_TOKEN;
            System.out.println("write header");
            String header = sc.nextLine();
            if(header.equals("Logout")){
                Request requestLogout = new Request<>("Logout",null);
                client.send(JSON.makeJsonFormat(requestLogout));
                flag = false;}
            if(header.equals("buy product")){
                System.out.println("write product name");
                String nameOfProduct = sc.nextLine();
                Request<BuyProductPayload> buyProductPayloadRequest = new Request<>("buy product",new
                        BuyProductPayload(nameOfProduct,token));
                client.send(JSON.makeJsonFormat(buyProductPayloadRequest));
            }
            if(header.equals("see products")){
                Request<SeeProductsPayload> seeProductsPayloadRequest = new Request<>("see products",
                        new SeeProductsPayload(token));
                client.send(JSON.makeJsonFormat(seeProductsPayloadRequest));
            }
            if(header.equals("add product")){
                System.out.println("Write product name");
                String nameOfProduct = sc.nextLine();
                Request<AddProductPayload> addProductPayloadRequest = new Request<>("add product",
                        new AddProductPayload(nameOfProduct,token));
                client.send(JSON.makeJsonFormat(addProductPayloadRequest));
            }
            if(header.equals("delete product")){
                System.out.println("Write product name");
                String nameOfProduct = sc.nextLine();
                Request<DeleteProductPayload> deleteProductPayloadRequest = new Request<>("delete product",
                        new DeleteProductPayload(nameOfProduct,token));
            }
        }
    }
}
