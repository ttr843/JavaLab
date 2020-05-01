package ru.itis.javalab.FakeInstagram.repository.Implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import ru.itis.javalab.FakeInstagram.model.Role;
import ru.itis.javalab.FakeInstagram.model.State;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.interfaces.UserRepository;


import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

@Repository
public class UserRepositoryImpl implements UserRepository {

    private static final String SQL_SAVE = "INSERT INTO users(" +
            "name, surname, email, hashpassword, role, state, photo_path, confirm_code) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_FIND_BY_EMAIL = "SELECT * FROM users WHERE email = ?";
    private static final String SQL_UPDATE_STATUS = "UPDATE users set state = ? where id = ?";
    private static final String SQL_FIND_BY_CONFIRM_CODE = "SELECT * from users where confirm_code = ?";
    private static final String SQL_FIND_BY_ID = "SELECT * from users where id = ?";
    private static final String SQL_UPDATE_USER_INFO = "update users set name = ?,surname = ?,email = ?," +
            "hashpassword = ?, photo_path = ? where id = ?";
    private static final String SQL_FIND_ALL = "select * from users";
    private JdbcTemplate jdbcTemplate;
    private BCryptPasswordEncoder passwordEncoder;
    private RowMapper<User> userRowMapper = (row, rowNumber) ->
            User.builder()
                    .id(row.getLong("id"))
                    .name(row.getString("name"))
                    .surname(row.getString("surname"))
                    .hashPassword(row.getString("hashpassword"))
                    .email(row.getString("email"))
                    .state(Enum.valueOf(State.class, row.getString("state")))
                    .role(Enum.valueOf(Role.class, row.getString("role")))
                    .photoPath(row.getString("photo_path"))
                    .confirm_code(row.getString("confirm_code"))
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
                statement.setString(1, user.getName());
                statement.setString(2, user.getSurname());
                statement.setString(3, user.getEmail());
                statement.setString(4, passwordEncoder.encode(user.getPassword()));
                statement.setString(5, user.getRole().toString());
                statement.setString(6, user.getState().toString());
                statement.setString(7, user.getPhotoPath());
                statement.setString(8, user.getConfirm_code());
                return statement;
            });
        }
    }


    private static final String SQL_FIND_USER_BY_EMAIL = "SELECT t1.id as userId,t1.name as name,t1.surname as surname,\n" +
            "            t1.email,t1.hashpassword,t1.role,t1.state,t1.photo_path,t1.confirm_code,\n" +
            "            t2.id as postId,t2.idpublicator as idpublicator,\n" +
            "            t2.text,t2.likeofpost,t2.date,t2.place,t2.photo as postphoto from users\n" +
            "    as t1 left join posts as t2 on t1.id = t2.idpublicator where t1.email = ?  ";
    @Override
    public Optional<User> findByEmail(String email) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email}, userRowMapper);
            return Optional.of(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateStatus(Long id, String status) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_STATUS);
            preparedStatement.setString(1, status);
            preparedStatement.setLong(2, id);
            return preparedStatement;
        });
    }


    @Override
    public Optional<User> findById(Long id) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{id}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public Optional<User> findByConfirmCode(String confirmCode) {
        try {
            User user = jdbcTemplate.queryForObject(SQL_FIND_BY_CONFIRM_CODE, new Object[]{confirmCode}, userRowMapper);
            return Optional.ofNullable(user);
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }


    @Override
    public void updateProfileData(User user) {
        jdbcTemplate.update(connection -> {
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_UPDATE_USER_INFO);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3,user.getEmail());
            preparedStatement.setString(4,user.getHashPassword());
            preparedStatement.setString(5,user.getPhotoPath());
            preparedStatement.setLong(6,user.getId());
            return preparedStatement;
        });
    }



    @Override
    public List<User> findAll() {
        return jdbcTemplate.query(SQL_FIND_ALL,userRowMapper);
    }
}
