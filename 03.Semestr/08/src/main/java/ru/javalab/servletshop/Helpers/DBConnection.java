package ru.javalab.servletshop.Helpers;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection connection;

    private DBConnection(){

    }

    public static Connection getConnection(){
        if(connection == null) {
            try {
                Class.forName("org.postgresql.Driver");
                Connection conn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/servletshop",
                        "postgres",
                        "postgres"
                );
                return conn;
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
