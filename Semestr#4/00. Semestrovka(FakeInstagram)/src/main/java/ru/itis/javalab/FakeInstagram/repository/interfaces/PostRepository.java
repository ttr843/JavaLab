package ru.itis.javalab.FakeInstagram.repository.interfaces;

import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.repository.interfaces.CrudRepository;

import java.util.List;

public interface PostRepository extends CrudRepository<Long, Post> {
    List<Post> findPostsByUserId(Long id);

    Post findPostById(Long postId);

    List<Post> findPostsByHashTag(String hashtag);

    void addPostToFavorites(long idPost,long idUser);

    List<Post> findAllFavoritesPosts(Long id);
}
