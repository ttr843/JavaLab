package ru.itis.javalab.EmailHomeWork.repositories;

import ru.itis.javalab.EmailHomeWork.models.User;

import java.util.Optional;

public interface UserRepository extends CrudRepository<Long, User> {
    void updateStatus(Long id, boolean status);
    Optional<User> findByConfirmCode(String confirmCode);
}
