package ru.itis.javalab.FakeInstagram.repository.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.FakeInstagram.dto.MessageDto;
import ru.itis.javalab.FakeInstagram.model.Message;
import ru.itis.javalab.FakeInstagram.repository.interfaces.MessageRepository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class MessageRepositoryImpl implements MessageRepository {
    private static final String SQL_SAVE = "insert into chatmessages(userid, pageid, text) " +
            "VALUES(?,?,?) ";
    private static final String SQL_FIND_UNREAD = "select * from chatmessages";

    private static final String SQL_UPDATE_STATUS = "UPDATE chatmessages set status = true where id = ?";

    private static final String SQL_SELECT_ALL_PAGES_IDS = "select pageid from chatmessages";

    private RowMapper<Message> messageRowMapper = (row, rowNumber) ->
            Message.builder()
                    .id(row.getLong("id"))
                    .pageId(row.getString("pageid"))
                    .text(row.getString("text"))
                    .userId(row.getLong("userid"))
                    .build();

    private RowMapper<String> pageRowMapper = (row,number) ->
            String.valueOf(row.getString("pageid"));

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<MessageDto> findAllMessages() {
        try {
            List<Message> messages = jdbcTemplate.query(SQL_FIND_UNREAD,messageRowMapper);
            List<MessageDto> messageDtos = new ArrayList<>();
            for(Message message: messages) {
                messageDtos.add(message.transformToMessageDto());
            }
            return messageDtos;
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException(0);
        }
    }

    @Override
    public void updateStatusOfMessage(Long id) {
            jdbcTemplate.update(connection -> {
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS);
                preparedStatement.setLong(1, id);
                return preparedStatement;
            });
    }

    @Override
    public List<String> getAllPageIds() {
        List<String> pageIds = jdbcTemplate.query(SQL_SELECT_ALL_PAGES_IDS,pageRowMapper);
        return pageIds;
    }


    @Override
    public void save(Message entity) {
        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection
                    .prepareStatement(SQL_SAVE,
                            Statement.RETURN_GENERATED_KEYS);
            statement.setLong(1, entity.getUserId());
            statement.setString(2, entity.getPageId());
            statement.setString(3, entity.getText());
            return statement;
        });

    }

}

