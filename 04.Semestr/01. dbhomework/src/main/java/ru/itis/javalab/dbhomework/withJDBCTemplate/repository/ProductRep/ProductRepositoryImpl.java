package ru.itis.javalab.dbhomework.withJDBCTemplate.repository.ProductRep;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.javalab.dbhomework.model.Product;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    private static final String SQL_FIND_ALL = "SELECT * FROM product";
    private static final String SQL_FIND_BY_ID = "SELECT * FROM product where id= ?";


    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Optional<Product> findOne(Integer id) {
        Product product = (Product) jdbcTemplate.queryForObject(SQL_FIND_BY_ID, new ProductRowMapper());
        return Optional.ofNullable(product);
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = jdbcTemplate.query(SQL_FIND_ALL, new ProductRowMapper());
        return products;
    }
}
