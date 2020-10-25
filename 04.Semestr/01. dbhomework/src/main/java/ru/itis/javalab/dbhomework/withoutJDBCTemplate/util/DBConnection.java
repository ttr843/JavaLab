package ru.itis.javalab.dbhomework.withoutJDBCTemplate.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static Connection instance;

    private DBConnection() {
    }

    public static Connection getInstance() {
        if (instance == null) {
            try {
                instance = DriverManager.getConnection("" +
                        "user=" +
                        "password=");
            } catch (SQLException e) {
                System.out.println(e);
                System.out.println("Failed to connect to database");
            }
        }
        return instance;
    }
}
