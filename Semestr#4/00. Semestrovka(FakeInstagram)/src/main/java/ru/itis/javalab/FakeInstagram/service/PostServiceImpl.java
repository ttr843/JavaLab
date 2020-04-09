package ru.itis.javalab.FakeInstagram.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.PostDto;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.repository.PostRepository;

import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private UploadService uploadService;

    @Autowired
    private PostRepository postRepository;
    @Override
    public void savePost(PostDto postDto, MultipartFile multipartFile, long userId) {
        Post post = Post.builder()
                .idPublicator(userId)
                .text(postDto.getText())
                .place(postDto.getPlace())
                .photo(uploadService.saveFile(multipartFile))
                .build();
        postRepository.save(post);
    }

    @Override
    public List<Post> getPostByUser(Long userId) {
        List<Post> posts = postRepository.findPostsByUserId(userId);
        return posts;
    }

    @Override
    public Post findPostById(Long postId) {
        return postRepository.findPostById(postId);
    }
}
