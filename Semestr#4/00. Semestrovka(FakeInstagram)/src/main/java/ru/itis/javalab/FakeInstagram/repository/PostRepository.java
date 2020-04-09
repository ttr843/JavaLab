package ru.itis.javalab.FakeInstagram.repository;

import ru.itis.javalab.FakeInstagram.model.Post;

import java.util.List;

public interface PostRepository extends CrudRepository<Long, Post>  {
    List<Post> findPostsByUserId(Long id);

    Post findPostById(Long postId);
}
