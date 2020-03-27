package ru.itis.javalab.springSecurityHomework.repository;


import ru.itis.javalab.springSecurityHomework.model.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Long, User> {
    Optional<User> findByEmail(String email);
}
