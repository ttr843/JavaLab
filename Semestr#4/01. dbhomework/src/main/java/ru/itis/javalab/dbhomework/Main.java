package ru.itis.javalab.dbhomework;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.itis.javalab.dbhomework.model.Product;
import ru.itis.javalab.dbhomework.model.User;
import ru.itis.javalab.dbhomework.withJDBCTemplate.repository.UserRep.UserRepository;
import ru.itis.javalab.dbhomework.withJDBCTemplate.repository.UserRep.UserRepositoryImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {

        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUsername("postgres");
        dataSource.setPassword("postgres");
        dataSource.setUrl("jdbc:postgresql://localhost:5432/Javalabhomework");
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        UserRepository userRepository = new UserRepositoryImpl();
        userRepository.setDataSource(dataSource);
        List<User> userList = userRepository.findAllByOneSelect();
        System.out.println("CHECK");
        for(User x: userList) {
            System.out.println(x.toString());
        }
    }
}
