package ru.javalab.socketapp.database.DAO;

import ru.javalab.socketapp.database.model.Message;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class MessageDAO {
    private Connection connection;

    public MessageDAO(Connection connection) {
        this.connection = connection;
    }

    public void save(Message message) {
        String sqlQuery = "INSERT INTO messages(text, iduser, date) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setString(1, message.getText());
            stmt.setInt(2, message.getIdUser());
            stmt.setString(3, new Date(System.currentTimeMillis()).toString());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
