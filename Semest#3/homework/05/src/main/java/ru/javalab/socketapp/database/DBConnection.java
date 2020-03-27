package ru.javalab.socketapp.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {
    private static Connection instance;
    private static String ip;
    private static int port;
    private static String user;
    private static String password;

    private DBConnection() {
    }

    public static Connection getInstance() {
        if (instance == null) {
            try {
                Class.forName("org.postgresql.Driver");
                instance = DriverManager.getConnection("jdbc:postgresql://" + ip + ":"
                                + port + "/SocketDB",
                        user,
                        password
                );
            } catch (SQLException | ClassNotFoundException e) {
                System.out.println(e);
                throw new IllegalArgumentException(e);
            }
        }
        return instance;
    }

    public static Connection createInstance(Properties properties) {
        ip = properties.getProperty("ip");
        port = Integer.parseInt(properties.getProperty("port"));
        user = properties.getProperty("user");
        password = properties.getProperty("password");
        return getInstance();
    }
}