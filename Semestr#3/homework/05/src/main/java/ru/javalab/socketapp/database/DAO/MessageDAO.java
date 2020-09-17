package ru.javalab.socketapp.database.DAO;

import ru.javalab.socketapp.database.model.Message;

import java.sql.*;
import java.util.ArrayList;

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

    public ArrayList<Message> find(int page, int size) {
        String sql = "SELECT * FROM messages ORDER BY 'date' LIMIT ?,?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, page);
            pstm.setInt(2, size);
            ResultSet rs = pstm.executeQuery();
            ArrayList<Message> messages = new ArrayList<>();
            while (rs.next()) {
                messages.add(new Message(rs.getInt("id"),
                        rs.getInt("iduser"),
                        rs.getString("text"),
                        rs.getString("date")));
            }
            return messages;
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }
}
