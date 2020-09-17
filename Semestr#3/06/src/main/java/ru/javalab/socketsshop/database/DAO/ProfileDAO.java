package ru.javalab.socketsshop.database.DAO;

import ru.javalab.socketsshop.database.ORM.Profile;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProfileDAO {
    private static Connection connection;

    public ProfileDAO(Connection connection) {
        this.connection = connection;
    }


    public static void save(Profile profile) {
        String sqlQuery = "INSERT INTO profiles(username, password, role) VALUES (?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setString(1, profile.getUsername());
            stmt.setString(2, profile.getPassword());
            stmt.setString(3,profile.getRole());
            stmt.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
    public static Profile find(String name) {
        String sqlQuery = "SELECT * FROM profiles WHERE username = ? LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(sqlQuery)) {
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            Profile profile = null;
            if (rs.next()) {
                profile = new Profile(rs.getInt("id"),
                        rs.getString("username"),
                        rs.getString("password"),
                        rs.getString("role"));
            }
            return profile;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

}
