package service;

import context.Component;
import helper.DbConnection;
import lombok.NoArgsConstructor;
import model.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@NoArgsConstructor
public class UserService implements Component {
    private Connection connection = new DbConnection().getConnection();

    public boolean checkUser(String login)  {
        try {
            PreparedStatement ps = connection.prepareStatement("select * from users where login = (?)");
            ps.setString(1, login);
            return ps.executeQuery().next();
        } catch (SQLException e) {
            throw new IllegalArgumentException(e);
        }

    }

    public boolean checkPassword(String login, String password) throws SQLException {
        Connection connection = new DbConnection("db.properties").getConnection();
        PreparedStatement ps = connection.prepareStatement("select * from users where login = (?)");
        int i = 1;
        ps.setString(i, login);
        ResultSet resultSet = ps.executeQuery();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return resultSet.next() && encoder.matches(password, resultSet.getString("password"));
    }
}
