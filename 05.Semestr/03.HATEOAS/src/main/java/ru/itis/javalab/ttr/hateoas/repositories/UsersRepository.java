package ru.itis.javalab.ttr.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.ttr.hateoas.models.User;

public interface UsersRepository extends JpaRepository<User,Long> {
}
