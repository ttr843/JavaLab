package ru.javalab.socketsshop.server;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.javalab.socketsshop.Helpers.DBConnection;
import ru.javalab.socketsshop.Helpers.JSON;
import ru.javalab.socketsshop.JSONProtocol.*;
import ru.javalab.socketsshop.database.DAO.BuyDAO;
import ru.javalab.socketsshop.database.DAO.ProductDAO;
import ru.javalab.socketsshop.database.DAO.ProfileDAO;
import ru.javalab.socketsshop.database.ORM.Buy;
import ru.javalab.socketsshop.database.ORM.Product;
import ru.javalab.socketsshop.database.ORM.Profile;


import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread extends Thread {
    private Socket clientSocket;
    private BufferedReader reader;
    private int userId;
    private String userName;
    private String role;
    public static String USER_TOKEN;

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
                ObjectMapper objectMapper = new ObjectMapper();
                Request request = objectMapper.readValue(line, Request.class);
                if (request.getHeader().equals("Login")) {
                    Request<LoginPayload> loginRequest = objectMapper.readValue(line, new TypeReference<Request<LoginPayload>>() {
                    });
                    String name = loginRequest.getPayload().getName();
                    String password = loginRequest.getPayload().getPassword();
                    ProfileDAO profileDAO = new ProfileDAO(DBConnection.getInstance());
                    Profile profile = profileDAO.find(name);
                    if (profile != null) {
                        PasswordEncoder encoder = new BCryptPasswordEncoder();
                        if (encoder.matches(password, profile.getPassword())) {
                            userId = profile.getId();
                            userName = profile.getUsername();
                            role = profile.getRole();
                            USER_TOKEN = JWT.create().withClaim("id", userId).withClaim
                                    ("role", role).sign(JSON.getAlgorithm());
                            Server.getClientList().add(this);
                            writer.println("You entered");
                        } else {
                            System.out.println("Incorrect Password");
                        }
                    } else {
                        PasswordEncoder encoder = new BCryptPasswordEncoder();
                        ProfileDAO.save(new Profile(0, name, encoder.encode(password), "user"));
                        userId = ProfileDAO.find(name).getId();
                        userName = ProfileDAO.find(name).getUsername();
                        USER_TOKEN = JWT.create().withClaim("id", userId).withClaim
                                ("role", role).sign(JSON.getAlgorithm());
                        Server.getClientList().add(this);
                        writer.println("You entered");
                    }
                } else if (request.getHeader().equals("Logout")) {
                    Server.logout(this);
                } else if (request.getHeader().equals("buy product")) {
                    Request<BuyProductPayload> buyProductPayloadRequest = objectMapper.readValue(line,
                            new TypeReference<Request<BuyProductPayload>>() {
                            });
                    DecodedJWT jwt = checkToken(
                            buyProductPayloadRequest.getPayload().getToken());
                    int id = jwt.getClaim("id").asInt();
                    String role = jwt.getClaim("role").asString();
                    BuyDAO buyDAO = new BuyDAO(DBConnection.getInstance());
                    ProductDAO productDAO = new ProductDAO(DBConnection.getInstance());
                    Product product = productDAO.find(buyProductPayloadRequest.getPayload().getNameOfProduct());
                    buyDAO.save(new Buy(id, product.getId()));

                } else if (request.getHeader().equals("see products")) {
                    Request<SeeProductsPayload> seeProductsPayloadRequest = objectMapper.readValue(line,
                            new TypeReference<Request<SeeProductsPayload>>() {
                            });
                    DecodedJWT jwt = checkToken(seeProductsPayloadRequest.getPayload().getToken());
                    int id = jwt.getClaim("id").asInt();
                    String role = jwt.getClaim("role").asString();
                    ProductDAO productDAO = new ProductDAO(DBConnection.getInstance());
                    ArrayList<Product> products = productDAO.getProducts();
                    if (!products.isEmpty()) {
                        writer.println(JSON.makeJsonFormat(new SeeProductResponse(products)));
                    } else {
                        writer.println("no products");
                    }
                } else if (request.getHeader().equals("add product")) {
                    Request<AddProductPayload> addProductPayloadRequest = objectMapper.readValue(line,
                            new TypeReference<Request<AddProductPayload>>() {
                            });
                    DecodedJWT jwt = checkToken(addProductPayloadRequest.getPayload().getToken());
                    int id = jwt.getClaim("id").asInt();
                    String role = jwt.getClaim("role").asString();
                    if (role.equals("admin")) {
                        ProductDAO productDAO = new ProductDAO(DBConnection.getInstance());
                        productDAO.save(new Product(0, addProductPayloadRequest.getPayload().getNameOfProduct()));
                    } else {
                        writer.println("You are not admin");
                    }
                } else if (request.getHeader().equals("delete product")) {
                    Request<DeleteProductPayload> deleteProductPayloadRequest = objectMapper.readValue(line,
                            new TypeReference<Request<DeleteProductPayload>>() {
                            });
                    DecodedJWT jwt = checkToken(deleteProductPayloadRequest.getPayload().getToken());
                    String role = jwt.getClaim("role").asString();
                    if (role.equals("admin")) {
                        ProductDAO productDAO = new ProductDAO(DBConnection.getInstance());
                        productDAO.delete(deleteProductPayloadRequest.getPayload().getNameOfProduct());
                    } else {
                        writer.println("You are not admin");
                    }
                }
            }
            reader.close();
            clientSocket.close();
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }

    private DecodedJWT checkToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(JSON.getAlgorithm()).build();
            verifier.verify(token);
            DecodedJWT jwt = JWT.decode(token);
            return jwt;
        } catch (JWTVerificationException e) {
            throw new IllegalArgumentException(e);
        }
    }
}

