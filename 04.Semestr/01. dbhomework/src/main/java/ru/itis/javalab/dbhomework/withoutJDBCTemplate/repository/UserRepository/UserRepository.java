package ru.itis.javalab.dbhomework.withoutJDBCTemplate.repository.UserRepository;

import ru.itis.javalab.dbhomework.model.User;
import ru.itis.javalab.dbhomework.withoutJDBCTemplate.repository.CrudRepositories;

import java.util.Optional;

public interface UserRepository extends CrudRepositories<User, Integer> {
    Optional<User> findByName(String name);

    void save(User user);
}
