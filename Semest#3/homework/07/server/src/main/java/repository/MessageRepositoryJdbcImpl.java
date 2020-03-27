package repository;

import Dto.UserDto;
import context.Component;
import helper.DbConnection;
import lombok.NoArgsConstructor;
import model.Message;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class MessageRepositoryJdbcImpl implements MessageRepository, Component {


    private Connection connection = new DbConnection().getConnection();

    @Override
    public void save(Message model) {
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO message(sender , text, timesent) VALUES (?, ?, ?)");
            int i = 1;
            st.setInt(i, model.getSender().getId());
            st.setString(++i, model.getText());
            st.setDate(++i, new Date(System.currentTimeMillis()));
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public Message findByID(int id){
        return null;
    }

    @Override
    public void delete(Message model) {

    }

    @Override
    public List<Message> findAll() {
        List<Message> messages = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM message ");
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

    @Override
    public void update() {
    }

}
