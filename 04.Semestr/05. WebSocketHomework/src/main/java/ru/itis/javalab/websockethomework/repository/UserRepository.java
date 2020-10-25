package ru.itis.javalab.websockethomework.repository;

import ru.itis.javalab.websockethomework.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findUserByEmail(String email);
}
