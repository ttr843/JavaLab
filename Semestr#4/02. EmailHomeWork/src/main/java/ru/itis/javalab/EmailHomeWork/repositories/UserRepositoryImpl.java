package ru.itis.javalab.EmailHomeWork.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Component;
import ru.itis.javalab.EmailHomeWork.models.User;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_INSERT = "Insert into java_user(name,email,password,email_status,confirmcode)" +
            " values(?,?,?,?,?)";
    private static final String SQL_UPDATE_STATUS = "UPDATE java_user set email_status = ? where id = ?";
    private static final String SQL_FIND_BY_CONFIRM_CODE = "SELECT * from java_user where confirmcode = ?";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .email(row.getString("email"))
                    .password(row.getString("password")).build();

    @Override
    public Optional<User> find(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User entity) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT);
            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getEmail());
            preparedStatement.setString(3, entity.getPassword());
            preparedStatement.setBoolean(4, false);
            preparedStatement.setString(5, entity.getConfirmCode());
            return preparedStatement;
        }, keyHolder);
        entity.setId((Long) keyHolder.getKey());
    }


    @Override
    public void delete(Long aLong) {

    }

    @Override
    public void updateStatus(Long id, boolean status) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS);
            preparedStatement.setBoolean(1, status);
            preparedStatement.setLong(2, id);
            return preparedStatement;
        });

    }

    @Override
    public Optional<User> findByConfirmCode(String confirmCode) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_CONFIRM_CODE, new Object[]{confirmCode}, userRowMapper);
            return Optional.ofNullable(user);
        }catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
