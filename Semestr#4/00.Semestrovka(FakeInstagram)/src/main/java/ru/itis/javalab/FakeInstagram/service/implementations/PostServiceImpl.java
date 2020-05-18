package ru.itis.javalab.FakeInstagram.service.implementations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.CommentDto;
import ru.itis.javalab.FakeInstagram.dto.PostDto;
import ru.itis.javalab.FakeInstagram.model.Comment;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.interfaces.CommentRepository;
import ru.itis.javalab.FakeInstagram.repository.interfaces.PostRepository;
import ru.itis.javalab.FakeInstagram.service.interfaces.UploadService;
import ru.itis.javalab.FakeInstagram.service.interfaces.PostService;

import java.util.Date;
import java.util.List;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private UploadService uploadService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;
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

    @Override
    public void saveComment(CommentDto commentDto, User user) {
        Date date = new Date(System.currentTimeMillis());
        Comment comment = Comment.builder()
                .idPost(commentDto.getIdPost())
                .namePublicator(user.getName() + " " + user.getSurname())
                .date(date.toString())
                .text(commentDto.getText())
                .build();
        commentRepository.save(comment);
    }

    @Override
    public List<Comment> findAllCommentsOfPost(long id) {
        return commentRepository.findAllCommentsOfPost(id);
    }

    @Override
    public void savePostToFavorites(long id, User user) {
        postRepository.addPostToFavorites(id,user.getId());
    }

    @Override
    public List<Post> findAllFavoritesPosts(User user) {
        return postRepository.findAllFavoritesPosts(user.getId());
    }
}
