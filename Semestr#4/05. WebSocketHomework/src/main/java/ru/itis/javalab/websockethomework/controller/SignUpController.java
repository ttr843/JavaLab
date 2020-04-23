package ru.itis.javalab.websockethomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.websockethomework.service.UserService;
import ru.itis.javalab.websockethomework.dto.UserDto;

@Controller
public class SignUpController {
    private UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/sign-up")
    public String getSignUp() {
        return "/signUp";
    }

    @PostMapping("/sign-up")
    public String signUp(UserDto userDto) {
        userService.signUp(userDto);
        return "redirect:/sign-in";
    }
}
