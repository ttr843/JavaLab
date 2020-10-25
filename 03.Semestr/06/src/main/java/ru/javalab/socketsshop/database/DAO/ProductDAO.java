package ru.javalab.socketsshop.database.DAO;

import ru.javalab.socketsshop.database.ORM.Product;
import ru.javalab.socketsshop.database.ORM.Profile;

import java.sql.*;
import java.util.ArrayList;

public class ProductDAO {
    private Connection connection;

    public ProductDAO(Connection connection) {
        this.connection = connection;
    }


    public void save(Product product) {
        String sqlQuery = "INSERT INTO products(name) VALUES (?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setString(1,product.getName());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public void delete(String name) {
        String sqlQuery = "DELETE FROM products where name=?";
        try(PreparedStatement stmt = connection.prepareStatement(sqlQuery)){
            stmt.setString(1,name);
            stmt.executeUpdate();
        }catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    public Product find(String name) {
        String sqlQuery = "SELECT * FROM products WHERE name = ? LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            Product product = null;
            if (rs.next()) {
                product = new Product(rs.getInt("id"),
                        rs.getString("name"));
            }
            return product;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public ArrayList<Product> getProducts(){
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM products");
            ArrayList<Product> products = new ArrayList<>();
            while (rs.next()){
                products.add(new Product(rs.getInt("id"),
                        rs.getString("name")));
            }
            return  products;
        } catch (SQLException e) {
            throw new IllegalStateException();
        }
    }

}
