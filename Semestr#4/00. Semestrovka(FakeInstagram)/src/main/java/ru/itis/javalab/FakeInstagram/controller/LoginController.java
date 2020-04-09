package ru.itis.javalab.FakeInstagram.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab.FakeInstagram.dto.UserDto;


@Controller
public class LoginController {


    @GetMapping("/login")
    public String login(Authentication authentication) {
        if(authentication != null) {
            return "redirect:/profile";
        }else{
            return "login";
        }
    }

    @PreAuthorize("permitAll()")
    @PostMapping("/login")
    public void login(@RequestParam UserDto userDto) {

    }
}
