package ru.javalab.socketsshop.database.DAO;

import ru.javalab.socketsshop.database.ORM.Buy;
import ru.javalab.socketsshop.database.ORM.Product;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BuyDAO {
    private Connection connection;

    public BuyDAO(Connection connection) {
        this.connection = connection;
    }


    public void save(Buy buy) {
        String sqlQuery = "INSERT INTO buy(idwho, idwhat) VALUES (?,?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setInt(1, buy.getIdWho());
            stmt.setInt(2, buy.getIdWhat());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
