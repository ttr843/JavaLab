package ru.itis.javalab.dbhomework.withJDBCTemplate.repository.ProductRep;

import org.springframework.jdbc.core.RowMapper;
import ru.itis.javalab.dbhomework.model.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet resultSet, int i) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getLong("id"));
        product.setName(resultSet.getString("name"));
        product.setDesc(resultSet.getString("desc"));
        product.setPrice(resultSet.getDouble("price"));
        product.setUserId(resultSet.getLong("userid"));
        return product;
    }
}
