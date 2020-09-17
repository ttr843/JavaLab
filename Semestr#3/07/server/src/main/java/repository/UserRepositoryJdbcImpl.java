package repository;


import context.Component;
import helper.DbConnection;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryJdbcImpl implements UserRepository, Component {

    private Connection connection = new DbConnection().getConnection();

    public UserRepositoryJdbcImpl() {
    }


    /*public UserRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }*/

    @Override
    public void save(User model)  {
        PreparedStatement st;
        try {
            st = connection.prepareStatement("INSERT INTO users(login, password, role) VALUES (?, ?, ?)");
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
        int i = 1;
        try {
            st.setString(i, model.getLogin());
            st.setString(++i, model.getPassword());
            st.setString(++i, model.getRole());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }


    }

    @Override
    public User findByID(int id) {

        for (User user : findAll()) {
            if (user.getId() == id) {
                return user;
            }
        }

        return null;
    }

    public User findByLogin(String login) {
        for (User user : findAll()) {
            if (user.getLogin().equals(login)) {
                return user;
            }
        }

        return null;
    }

    @Override
    public void delete(User model) {

    }

    @Override
    public List<User> findAll() {

        List<User> users = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM users ");
            while (resultSet.next()) {
                users.add(new User(resultSet.getInt("id"), resultSet.getString("login"),
                        resultSet.getString("password"), resultSet.getString("role")));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }


        return users;
    }

    @Override
    public void update() {

    }
}
