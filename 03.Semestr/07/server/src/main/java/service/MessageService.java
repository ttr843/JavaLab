package service;

import Dto.UserDto;
import context.Component;
import helper.DbConnection;
import lombok.NoArgsConstructor;
import model.Message;
import repository.UserRepositoryJdbcImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class MessageService implements Component {


    public List<Message> findByPage(int page, int size) {
        Connection connection = new DbConnection().getConnection();
        final String SQL_findMessagesOnPage = "SELECT * FROM message LIMIT " + size + "OFFSET " + (page - 1) * size + ";";
        List<Message> messages = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_findMessagesOnPage)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                messages.add(new Message(UserDto.from(new UserRepositoryJdbcImpl().findByID(resultSet.getInt("sender"))),
                        resultSet.getString("text"),
                        resultSet.getDate("timesent")));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        return messages;
    }
}
