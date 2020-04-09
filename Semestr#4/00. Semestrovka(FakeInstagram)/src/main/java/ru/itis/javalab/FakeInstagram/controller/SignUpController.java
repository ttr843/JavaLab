package ru.itis.javalab.FakeInstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.UserDto;
import ru.itis.javalab.FakeInstagram.service.SignUpService;

@Controller
public class SignUpController {


    @Autowired
    private SignUpService signUpService;


    @GetMapping("/signUp")
    public String signUp(Authentication authentication) {
        if(authentication != null) {
            return "redirect:/profile";
        }else{
            return "signUp";
        }
    }


    @PostMapping(value = "/signUp", consumes = "multipart/form-data")
    public String signUp(UserDto userDto, @RequestParam("file") MultipartFile multipartFile) {
        signUpService.signUp(userDto, multipartFile);
        return "to_confirm";
    }
}
