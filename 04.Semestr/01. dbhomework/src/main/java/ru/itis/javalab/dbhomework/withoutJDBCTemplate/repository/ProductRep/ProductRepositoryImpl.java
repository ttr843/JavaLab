package ru.itis.javalab.dbhomework.withoutJDBCTemplate.repository.ProductRep;

import ru.itis.javalab.dbhomework.model.Product;
import ru.itis.javalab.dbhomework.withoutJDBCTemplate.repository.RowMapper;
import ru.itis.javalab.dbhomework.withoutJDBCTemplate.util.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    private Connection connection;

    private static final String SQL_FINDALL = "SELECT * FROM product";

    private RowMapper<Product> productRowMapper = row -> new Product(
            row.getLong("id"),
            row.getString("name"),
            row.getDouble("price"),
            row.getString("desc"),
            row.getLong("userid")
    );

    public ProductRepositoryImpl() {
        connection = DBConnection.getInstance();
    }

    @Override
    public Optional<Product> findOne(Integer integer) {
        return Optional.empty();
    }

    @Override
    public List<Product> findAll() {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_FINDALL)) {
            ResultSet rs = stmt.executeQuery();
            ArrayList<Product> productList = new ArrayList<>();
            while (rs.next()) {
                productList.add(productRowMapper.mapRow(rs));
            }
            connection.close();
            stmt.close();
            rs.close();
            return Optional.of(productList).orElse(new ArrayList<>());
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
