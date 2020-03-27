package repository;

import Dto.UserDto;
import context.Component;
import helper.DbConnection;
import lombok.NoArgsConstructor;
import model.Message;
import model.Product;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ProductRepositoryJdbcImpl implements ProductRepository, Component {
    private Connection connection = new DbConnection().getConnection();

    @Override
    public void save(Product model) {
        try {
            PreparedStatement st = connection.prepareStatement("INSERT INTO product(name, price) VALUES (?, ?)");
            int i = 1;
            st.setString(i, model.getName());
            st.setInt(++i, model.getPrice());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    @Override
    public Product findByID(int id) {
        for (Product product : findAll()) {
            if (product.getId() == id)
                return product;
        }
        return null;
    }

    @Override
    public void delete(Product model) {
        try {
            PreparedStatement st = connection.prepareStatement("DELETE from product where id = ?");
            int i = 1;
            st.setInt(i, model.getId());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public List<Product> findAll() {
        List<Product> products = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet;
            resultSet = statement.executeQuery("SELECT * FROM product ");
            while (resultSet.next()) {
                products.add(new Product(resultSet.getInt("id"), resultSet.getString("name"),
                        resultSet.getInt("price")));
            }
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

        return products;
    }

    @Override
    public void update() {

    }

}
