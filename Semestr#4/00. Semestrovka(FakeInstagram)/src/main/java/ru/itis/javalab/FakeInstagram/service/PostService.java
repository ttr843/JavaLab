package ru.itis.javalab.FakeInstagram.service;

import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.PostDto;
import ru.itis.javalab.FakeInstagram.model.Post;

import java.util.List;

public interface PostService {
    void savePost(PostDto postDto, MultipartFile multipartFile, long userId);
    List<Post> getPostByUser(Long userId);
    Post findPostById(Long postId);
}
