package ru.itis.javalab.FakeInstagram.mvcController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.SearchDto;
import ru.itis.javalab.FakeInstagram.dto.SubDto;
import ru.itis.javalab.FakeInstagram.dto.UserDto;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.repository.interfaces.UserRepository;
import ru.itis.javalab.FakeInstagram.security.detail.UserDetailsImpl;
import ru.itis.javalab.FakeInstagram.service.interfaces.PostService;
import ru.itis.javalab.FakeInstagram.service.interfaces.ProfileService;
import ru.itis.javalab.FakeInstagram.service.interfaces.SearchService;

import java.util.List;
import java.util.Optional;

@Controller
@Profile("mvc")
public class ProfileController {

    @Autowired
    private PostService postService;

    @Autowired
    private ProfileService profileService;

    @Autowired
    private SearchService searchService;

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

    @PostMapping(value = "/editProfile" )
    public String editProfile(UserDto userDto, Authentication
            authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        profileService.editProfile(userDto,user);
        User updatedUser = new User();
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            updatedUser = optionalUser.get();
        }
        ((UserDetailsImpl) authentication.getPrincipal()).setUser(updatedUser);
        return "redirect:/profile";
    }

    @GetMapping(value = "/search")
    public String search() {
        return "redirect:/profile";
    }

    @PostMapping(value = "/search")
    public String search(SearchDto searchDto, Model model, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("user", user);
        if (searchDto.getKeyWord().contains("#")) {
            List<Post> posts = searchService.searchPosts(searchDto);
            if (posts.isEmpty()) {
                model.addAttribute("error", "posts not found");
                return "search";
            } else {
                model.addAttribute("posts", posts);
                return "search";
            }
        } else {
            User searchedUser = searchService.searchProfile(searchDto);
            if (searchedUser == null) {
                model.addAttribute("error", "user not found");
                return "search";
            } else {
                if (searchedUser.getId() == user.getId()) {
                    return "redirect:/profile";
                } else {
                    model.addAttribute("searchedUser", searchedUser);
                    return "search";
                }
            }
        }

    }

    @GetMapping(value = "/subscribe")
    public String subscribe() {
        return "redirect:/profile";
    }

    @GetMapping(value = "/subscriptions")
    public String subscribes(Authentication authentication, Model model) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("user", user);
        model.addAttribute("subs", searchService.searchSubscriptions(user));
        return "subscriptions";
    }

    @PostMapping(value = "/subscribe")
    public String subscribe(SubDto subDto, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        profileService.subscribe(subDto, user);
        return "redirect:/subscriptions";
    }

    @PostMapping(value = "/deleteSub")
    public String deleteSub(SubDto subDto, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        profileService.deleteSub(subDto,user);
        return "redirect:/subscriptions";
    }

    @Autowired
    private UserRepository userRepository;

    @PostMapping(value = "/addPhotoToAvatar", consumes = "multipart/form-data")
    public String deleteSub(@RequestParam("file") MultipartFile multipartFile, Authentication authentication){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        profileService.updatePhotoAvatar(multipartFile,user);
        User updatedUser = new User();
        Optional<User> optionalUser = userRepository.findById(user.getId());
        if (optionalUser.isPresent()) {
            updatedUser = optionalUser.get();
        }
        ((UserDetailsImpl) authentication.getPrincipal()).setUser(updatedUser);
        return "redirect:/profile";
    }
}
