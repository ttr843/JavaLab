package ru.javalab.servletshop.repository;

import ru.javalab.context.Component;
import ru.javalab.servletshop.Helpers.DBConnection;
import ru.javalab.servletshop.Helpers.PasswordHasher;
import ru.javalab.servletshop.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class UserRepositoryImpl implements UserRepository, Component {
    private Connection dbConnection;

    public UserRepositoryImpl() {
        dbConnection = DBConnection.getConnection();
    }


    @Override
    public User findOne(Integer integer) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User model) {
        String sqlQuery = "insert into users(login, password) values (?,?)";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sqlQuery)) {
            stmt.setString(1, model.getLogin());
            stmt.setString(2, PasswordHasher.generateHashedPassword(model.getPassword()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void delete(User model) {

    }

    @Override
    public void update() {

    }

    @Override
    public User findByName(String name) {
        String sqlQuery = "SELECT * from users where login=? LIMIT 1";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sqlQuery)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            User user = null;
            if (rs.next()) {
                user = new User(rs.getInt("id"),rs.getString("login"),rs.getString("password"));
            }
            return user;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
