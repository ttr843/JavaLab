package ru.itis.javalab.dbhomework.withoutJDBCTemplate.repository.UserRepository;

import ru.itis.javalab.dbhomework.model.Product;
import ru.itis.javalab.dbhomework.model.User;
import ru.itis.javalab.dbhomework.withoutJDBCTemplate.repository.ProductRep.ProductRepositoryImpl;
import ru.itis.javalab.dbhomework.withoutJDBCTemplate.repository.RowMapper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private Connection connection;

    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM user";
    private static final String SQL_FIND_BY_NAME = "SELECT * FROM user WHERE name = ? LIMIT 1";
    private static final String SQL_INSERT = "INSERT INTO user(email, password) VALUES (?, ?)";

    private ProductRepositoryImpl productRepository;

    private RowMapper<User> userRowMapper = rs -> new User(
            rs.getLong("id"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getString("password")
    );


    @Override
    public Optional<User> findOne(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<User> findAll() {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_FIND_ALL_USERS)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<User> userList = new ArrayList<>();
            List<Product> products = productRepository.findAll();
            while (rs.next()) {
                ArrayList<Product> userProducts = new ArrayList<>();
                for (Product product : products) {
                    if (rs.getLong("id") == product.getUserId()) {
                        userProducts.add(product);
                    }
                }
                User user = userRowMapper.mapRow(rs);
                user.setProducts(userProducts);
                userList.add(user);
            }
            connection.close();
            stmt.close();
            rs.close();
            return Optional.of(userList).orElse(new ArrayList<>());
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }


    @Override
    public Optional<User> findByName(String name) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_FIND_BY_NAME)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            User user = null;
            if (rs.next()) {
                user = userRowMapper.mapRow(rs);
            }
            connection.close();
            stmt.close();
            rs.close();
            return Optional.ofNullable(user);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(User user) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_INSERT)) {
            stmt.setString(1, user.getEmail());
            stmt.setString(2, user.getPassword());
            stmt.executeUpdate();
            connection.close();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
