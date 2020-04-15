package ru.itis.javalab.FakeInstagram.repository;


import ru.itis.javalab.FakeInstagram.model.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<Long, User> {
    Optional<User> findByEmail(String email);
    void updateStatus(Long id, String status);

    Optional<User> findById(Long id);

    Optional<User> findByConfirmCode(String confirmCode);
    void updateProfileData(User user);

    List<User> findAll();
}
