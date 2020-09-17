package ru.javalab.socketapp.database.DAO;

import ru.javalab.socketapp.database.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {
    private Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }


    public void save(User user) {
        String sqlQuery = "INSERT INTO users(username, password) VALUES (?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    public User find(String name) {
        String sqlQuery = "SELECT * FROM users WHERE username = ? LIMIT 1";
         try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password")) ;
            }
            return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
