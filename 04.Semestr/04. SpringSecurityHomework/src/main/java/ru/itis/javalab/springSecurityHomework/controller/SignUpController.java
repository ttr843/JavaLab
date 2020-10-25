package ru.itis.javalab.springSecurityHomework.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itis.javalab.springSecurityHomework.dto.UserDto;
import ru.itis.javalab.springSecurityHomework.service.UserService;

@Controller
public class SignUpController {
    private UserService userService;

    @Autowired
    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/signUp")
    public String signUp() {
        return "signUp";
    }

    @PostMapping("/signUp")
    public String signUp(UserDto userDto) {
        userService.signUp(userDto);
        return "redirect:/login";
    }
}
