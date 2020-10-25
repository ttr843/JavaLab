package ru.itis.javalab.FakeInstagram.repository.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.FakeInstagram.model.Sub;
import ru.itis.javalab.FakeInstagram.repository.interfaces.SubscriptionsRepository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository
public class SubscriptionsRepositoryImpl implements SubscriptionsRepository {

    private static final String SQL_ADD_SUBSCRIBE = "INSERT INTO subs(idtowho,idwho) VALUES (?,?)";
    private static final String SQL_FIND_BY_USER_ID = "SELECT * from subs where idwho = ?";
    private static final String SQL_DELETE_SUB = "DELETE FROM subs where idwho = ? and idtowho = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<Sub> subRowMapper = (row, rowNumber) ->
            Sub.builder()
                    .idWho(row.getLong("idwho"))
                    .idToWho(row.getLong("idtowho"))
                    .build();

    @Override
    public void save(Sub entity) {
    }

    @Override
    public void subscribe(long idToWho, long idWho) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_ADD_SUBSCRIBE);
            preparedStatement.setLong(1, idToWho);
            preparedStatement.setLong(2, idWho);
            return preparedStatement;
        });
    }

    @Override
    public void deleteSub(long idToWho, long idWho) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_DELETE_SUB);
            preparedStatement.setLong(1,idWho );
            preparedStatement.setLong(2, idToWho);
            return preparedStatement;
        });
    }

    @Override
    public List<Sub> findAllSubs(long id) {
        try {
            return jdbcTemplate.query(SQL_FIND_BY_USER_ID,new Object[]{id},subRowMapper);
        } catch (EmptyResultDataAccessException e) {
            throw new EmptyResultDataAccessException(0);
        }
    }
}
