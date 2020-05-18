package ru.itis.javalab.FakeInstagram.repository.interfaces;

import ru.itis.javalab.FakeInstagram.model.Comment;

import java.util.List;

public interface CommentRepository extends CrudRepository<Long, Comment> {
    List<Comment> findAllCommentsOfPost(long id);

}
