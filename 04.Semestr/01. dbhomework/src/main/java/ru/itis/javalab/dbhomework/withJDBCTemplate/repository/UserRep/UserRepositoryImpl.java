package ru.itis.javalab.dbhomework.withJDBCTemplate.repository.UserRep;

import org.springframework.jdbc.core.JdbcTemplate;
import ru.itis.javalab.dbhomework.model.Product;
import ru.itis.javalab.dbhomework.model.User;
import ru.itis.javalab.dbhomework.withJDBCTemplate.repository.ProductRep.ProductRepositoryImpl;

import javax.sql.DataSource;
import java.util.*;

public class UserRepositoryImpl implements UserRepository {

    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;


    private static final String SQL_FIND_ALL_USERS = "SELECT * FROM user";
    private static final String SQL_FIND_ALL_USERS_BY_ONE_SELECTION = "SELECT t1.id as userId,t1.name as username," +
            "t1.email,t1.password,t2.id as productId,t2.name as productname,\n" +
            "       t2.price,t2.\"desc\",t2.userid as userOfProductId from java_user as t1 left join product as t2 on t1.id = t2.userid";

    private ProductRepositoryImpl productRepository;

    @Override
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }


    @Override
    public Optional<User> findOne(Integer integer) {
        return Optional.empty();
    }

    public List<User> findAllByOneSelect() {
        List<User> users = jdbcTemplate.query(SQL_FIND_ALL_USERS_BY_ONE_SELECTION, resultSet ->
                resultSet.next() ? new UserJoinProductRowMapper().mapRow(resultSet) : null);
        return users;
    }

    @Override
    public List<User> findAll() {
        List<User> userList = jdbcTemplate.query(SQL_FIND_ALL_USERS, new UserRowMapper());
        List<Product> products = productRepository.findAll();
        for (User user : userList) {
            List<Product> productOfUser = new ArrayList<>();
            for (Product product : products) {
                if (user.getId() == product.getUserId()) {
                    productOfUser.add(product);
                }
            }
            user.setProducts((ArrayList<Product>) productOfUser);
        }
        return userList;
    }
}
