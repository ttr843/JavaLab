package ru.itis.javalab.ttr.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.ttr.hateoas.models.Favorite;

public interface FavoritesRepository extends JpaRepository<Favorite,Long> {
}
