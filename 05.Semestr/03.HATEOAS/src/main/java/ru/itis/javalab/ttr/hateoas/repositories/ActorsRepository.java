package ru.itis.javalab.ttr.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.ttr.hateoas.models.Actor;

public interface ActorsRepository extends JpaRepository<Actor,Long> {
}
