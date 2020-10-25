package ru.itis.javalab.dbhomework.withJDBCTemplate.repository.UserRep;


import org.springframework.dao.EmptyResultDataAccessException;
import ru.itis.javalab.dbhomework.model.Product;
import ru.itis.javalab.dbhomework.model.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;


class UserJoinProductRowMapper {
    List<User> mapRow(ResultSet row) {
        try {
            Map<Long, User> joinMap = new LinkedHashMap<>();
            do {
                if (row.getLong("userId") > 0) {
                    User user = new User(row.getLong("userId"), row.getString("username"),
                            row.getString("email"), row.getString("password"));
                    joinMap.putIfAbsent(user.getId(), user);
                }
                if (row.getLong("productId") > 0) {
                    Product product = new Product(row.getLong("productId"),
                            row.getString("productname"), row.getDouble("price"),
                            row.getString("desc"), row.getLong("userOfProductId"));
                    joinMap.get(product.getUserId()).setProduct(product);
                }
            } while (row.next());
            return new ArrayList<>(joinMap.values());
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}

