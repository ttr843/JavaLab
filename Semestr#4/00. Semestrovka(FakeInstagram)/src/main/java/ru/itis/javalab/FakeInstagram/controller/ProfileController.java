package ru.itis.javalab.FakeInstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.UserDto;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.security.detail.UserDetailsImpl;
import ru.itis.javalab.FakeInstagram.service.PostService;
import ru.itis.javalab.FakeInstagram.service.ProfileService;

import java.util.List;

@Controller
public class ProfileController {

    @Autowired
    private PostService postService;

    @Autowired
    private ProfileService profileService;

    @GetMapping("/profile")
    public String getProfile(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        List<Post> posts = postService.getPostByUser(user.getId());
        model.addAttribute("user", user);
        model.addAttribute("posts", posts);
        return "profile";
    }

    @GetMapping("/editProfile")
    public String editProfile(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("user", user);
        return "editProfile";
    }

    @PostMapping(value = "/editProfile", consumes = "multipart/form-data")
    public String editProfile(UserDto userDto, @RequestParam("file") MultipartFile multipartFile, Authentication
            authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        profileService.editProfile(userDto, multipartFile, user);
        return "redirect:/profile";
    }
}
