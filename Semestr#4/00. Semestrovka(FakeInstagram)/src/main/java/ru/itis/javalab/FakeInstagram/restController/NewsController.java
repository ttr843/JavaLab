package ru.itis.javalab.FakeInstagram.restController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
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
import ru.itis.javalab.FakeInstagram.service.interfaces.NewsService;

import java.util.List;

@RestController
@Profile("rest")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping(value = "/news")
    public ResponseEntity<List<Post>> getNews() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getDetails();
        User user = User.builder()
                .id(userDetails.getUserId())
                .build();
        return ResponseEntity.ok(newsService.showNews(user));
    }

}
