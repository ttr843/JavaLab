package ru.itis.javalab.FakeInstagram.service.interfaces;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.CommentDto;
import ru.itis.javalab.FakeInstagram.dto.PostDto;
import ru.itis.javalab.FakeInstagram.model.Comment;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.User;

import java.util.List;

public interface PostService {
    void savePost(PostDto postDto, MultipartFile multipartFile, long userId);
    List<Post> getPostByUser(Long userId);
    Post findPostById(Long postId);
    void saveComment(CommentDto commentDto, User user);
    List<Comment> findAllCommentsOfPost(long id);
    void savePostToFavorites(long id,User user);

    List<Post> findAllFavoritesPosts(User user);
}
