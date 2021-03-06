package ru.itis.javalab.FakeInstagram.mvcController;

import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.security.detail.UserDetailsImpl;


@Controller
@Profile("mvc")
public class ChatController {

    @GetMapping("/chat")
    public String getChatPage(Model model, Authentication authentication) {
        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        User user = userDetails.getUser();
        model.addAttribute("user", user);
        model.addAttribute("pageId", user.getId());
        return "chat";
    }
}