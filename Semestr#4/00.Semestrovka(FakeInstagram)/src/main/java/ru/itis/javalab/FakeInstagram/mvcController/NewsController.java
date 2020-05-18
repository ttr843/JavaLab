package ru.itis.javalab.FakeInstagram.mvcController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.javalab.FakeInstagram.model.Post;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.security.detail.UserDetailsImpl;
import ru.itis.javalab.FakeInstagram.service.interfaces.NewsService;

import java.util.List;

@Controller
@Profile("mvc")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping(value = "/news")
    public String showNews(Authentication authentication, Model model){
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("user", user);
        List<Post> posts = newsService.showNews(user);
        model.addAttribute("posts",posts);
        return "news";
    }
}
