package ru.itis.javalab.FakeInstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.CommentDto;
import ru.itis.javalab.FakeInstagram.dto.PostDto;
import ru.itis.javalab.FakeInstagram.model.Comment;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.interfaces.UserRepository;
import ru.itis.javalab.FakeInstagram.security.detail.UserDetailsImpl;
import ru.itis.javalab.FakeInstagram.service.interfaces.PostService;

import java.util.List;
import java.util.Optional;

@Controller
@Profile("mvc")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/addPost")
    public String getView(Authentication authentication, Model model) {
        if (authentication != null) {
            UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
            User user = userDetails.getUser();
            model.addAttribute("user", user);
            return "addPost";
        } else {
            return "redirect:/login";
        }
    }

    @RequestMapping(value = "/post/{post-id:.+}", method = RequestMethod.GET)
    public String getFile(@PathVariable("post-id") Long postId, Model model, Authentication authentication) {
        Post post = postService.findPostById(postId);
        Optional<User> userOptional = userRepository.findById(post.getIdPublicator());
        List<Comment> comments = postService.findAllCommentsOfPost(postId);
        model.addAttribute("comments", comments);
        model.addAttribute("user", userOptional.get());
        model.addAttribute("post", post);
        return "post";
    }

    @PostMapping(value = "/addPost", consumes = "multipart/form-data")
    public String addPost(PostDto postDto, @RequestParam("file") MultipartFile multipartFile,
                          Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        postService.savePost(postDto, multipartFile, user.getId());
        return "redirect:/";
    }

    @PostMapping(value = "/addComment")
    public String addComment(CommentDto commentDto, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        postService.saveComment(commentDto, user);
        return "redirect:/";
    }

    @RequestMapping(value = "/addToFavorites/{post-id:.+}", method = RequestMethod.POST)
    public String getFile(@PathVariable("post-id") Long postId,Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        postService.savePostToFavorites(postId,user);
        return "redirect:/post/" + postId;
    }

}
