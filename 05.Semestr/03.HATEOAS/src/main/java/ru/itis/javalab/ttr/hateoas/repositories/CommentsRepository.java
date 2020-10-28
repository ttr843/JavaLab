package ru.itis.javalab.ttr.hateoas.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itis.javalab.ttr.hateoas.models.Comment;

public interface CommentsRepository extends JpaRepository<Comment,Long> {
}
