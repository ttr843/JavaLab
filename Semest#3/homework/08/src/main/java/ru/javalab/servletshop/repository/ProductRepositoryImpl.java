package ru.javalab.servletshop.repository;

import ru.javalab.context.Component;
import ru.javalab.servletshop.Helpers.DBConnection;
import ru.javalab.servletshop.model.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository, Component {
    private Connection dbConnection;

    public ProductRepositoryImpl() {
        dbConnection = DBConnection.getConnection();
    }

    @Override
    public Product findOne(Integer integer) {
        return null;
    }

    @Override
    public List<Product> findAll() {
        String sqlQuery = "SELECT * FROM products";
        try (PreparedStatement stmt = dbConnection.prepareStatement(sqlQuery)) {
            ResultSet rs = stmt.executeQuery();
            List<Product> products = new ArrayList<>();
            while (rs.next()) {
                products.add(new Product(rs.getInt("id"), rs.getString("name"),
                        rs.getString("description"),rs.getInt("price")));
            }
            return products;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Product model) {

    }

    @Override
    public void delete(Product model) {

    }

    @Override
    public void update() {

    }
}
