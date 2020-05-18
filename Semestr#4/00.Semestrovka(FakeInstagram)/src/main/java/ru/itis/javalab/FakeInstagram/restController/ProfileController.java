package ru.itis.javalab.FakeInstagram.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.itis.javalab.FakeInstagram.dto.UserDto;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.restSecurity.jwt.details.UserDetailsImpl;
import ru.itis.javalab.FakeInstagram.service.interfaces.PostService;
import ru.itis.javalab.FakeInstagram.service.interfaces.SearchService;

import java.util.ArrayList;
import java.util.List;

@RestController
@Profile("rest")
public class ProfileController {

    @Autowired
    private PostService postService;

    @Autowired
    private SearchService searchService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/profile")
    public ResponseEntity<UserDto> getProfile() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        return ResponseEntity.ok(UserDto.builder()
                .name(userDetails.getUsername())
                .surname(userDetails.getSurname())
                .email(userDetails.getEmail())
                .password(userDetails.getHashPassword())
                .photo(userDetails.getPhotoPath())
                .id(userDetails.getUserId())
                .build());
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/profile/posts")
    public ResponseEntity<List<Post>> getProfilePost() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        UserDto userDto = UserDto.builder()
                .name(userDetails.getUsername())
                .surname(userDetails.getSurname())
                .email(userDetails.getEmail())
                .password(userDetails.getHashPassword())
                .photo(userDetails.getPhotoPath())
                .id(userDetails.getUserId())
                .build();
        return ResponseEntity.ok(postService.getPostByUser(userDto.getId()));
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/subs")
    public ResponseEntity<List<User>> getSubscriptions() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = User.builder()
                .id(userDetails.getUserId())
                .build();
        return ResponseEntity.ok(searchService.searchSubscriptions(user));
    }
}
