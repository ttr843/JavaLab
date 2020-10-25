package ru.itis.javalab.ttr.rabbitmqsecond.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConfig {
    private static Connection connection;

    private DBConfig(){

    }

    public static Connection getConnection(){
        if(connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                 connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/rabbitmq",
                        "postgres",
                        "postgres"
                );
                return connection;
            } catch (SQLException e) {
                throw new IllegalStateException(e);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
        return connection;
    }

    public static void closeConnection(Connection conn) {
        try {
            conn.close();
        }catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
