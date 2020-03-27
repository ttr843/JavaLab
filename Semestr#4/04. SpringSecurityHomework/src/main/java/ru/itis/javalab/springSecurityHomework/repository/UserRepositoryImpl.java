package ru.itis.javalab.springSecurityHomework.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import ru.itis.javalab.springSecurityHomework.model.User;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Optional;

@Component
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_SAVE = "INSERT INTO itis_user(email,hash_password) VALUES (?, ?)";
    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM itis_user WHERE email = ?";
    private JdbcTemplate jdbcTemplate;
    private BCryptPasswordEncoder passwordEncoder;
    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("id"))
                    .passwordHash(row.getString("hash_password"))
                    .email(row.getString("email"))
                    .build();


    @Autowired
    public UserRepositoryImpl(JdbcTemplate jdbcTemplate, BCryptPasswordEncoder passwordEncoder) {
        this.jdbcTemplate = jdbcTemplate;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void save(User user) {
        if (!findByEmail(user.getEmail()).isPresent()) {
            jdbcTemplate.update(connection -> {
                PreparedStatement statement = connection
                        .prepareStatement(SQL_SAVE,
                                Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, user.getEmail());
                statement.setString(2, passwordEncoder.encode(user.getPassword()));
                return statement;
            });
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, userRowMapper);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }
}
