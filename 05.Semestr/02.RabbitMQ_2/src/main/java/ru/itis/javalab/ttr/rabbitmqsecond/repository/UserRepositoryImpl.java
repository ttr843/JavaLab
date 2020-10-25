package ru.itis.javalab.ttr.rabbitmqsecond.repository;

import ru.itis.javalab.ttr.rabbitmqsecond.config.DBConfig;
import ru.itis.javalab.ttr.rabbitmqsecond.dto.UpdateDto;
import ru.itis.javalab.ttr.rabbitmqsecond.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class UserRepositoryImpl implements UserRepository {

    private final Connection connection;

    public UserRepositoryImpl() {
        this.connection = DBConfig.getConnection();
    }

    private static final String SQL_SAVE = "insert into profile values (?,?,?)";
    @Override
    public void save(User entity) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_SAVE)) {
            stmt.setString(1, entity.getFirstName());
            stmt.setString(2, entity.getLastName());
            stmt.setLong(3, entity.getPassportID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("can`t save user");
            throw new IllegalArgumentException(e);
        }
    }

    private static final String SQL_DELETE = "delete from profile where passportid = ? ";
    @Override
    public void delete(User entity) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_DELETE)) {
            stmt.setLong(1, entity.getPassportID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("can`t delete user");
            throw new IllegalArgumentException(e);
        }
    }

    private static final String SQL_UPDATE_PASS_ID = "update profile set passportID = ? where passportID = ?";
    @Override
    public void updateByUpdateDto(UpdateDto updateDto) {
        try (PreparedStatement stmt = connection.prepareStatement(SQL_UPDATE_PASS_ID)) {
            stmt.setLong(1,updateDto.getNewPassportID());
            stmt.setLong(2, updateDto.getPassportID());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("can`t update user");
            throw new IllegalArgumentException(e);
        }
    }
}
