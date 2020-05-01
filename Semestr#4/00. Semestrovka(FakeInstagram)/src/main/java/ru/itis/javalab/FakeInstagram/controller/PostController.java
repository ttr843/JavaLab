package ru.itis.javalab.FakeInstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.PostDto;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.interfaces.UserRepository;
import ru.itis.javalab.FakeInstagram.security.detail.UserDetailsImpl;
import ru.itis.javalab.FakeInstagram.service.interfaces.PostService;

import java.util.Optional;

@Controller
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

}
