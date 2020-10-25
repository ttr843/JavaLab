package ru.itis.javalab.websockethomework.repository;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.itis.javalab.websockethomework.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class UserRowMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet row, int rowNum) throws SQLException {
        return User.builder()
                .id(row.getLong("id"))
                .passwordHash(row.getString("hash_password"))
                .email(row.getString("email"))
                .name(row.getString("name"))
                .build();
    }
}
