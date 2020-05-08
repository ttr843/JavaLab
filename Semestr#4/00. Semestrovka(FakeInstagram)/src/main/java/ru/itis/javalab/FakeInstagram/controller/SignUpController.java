package ru.itis.javalab.FakeInstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.itis.javalab.FakeInstagram.dto.UserDto;
import ru.itis.javalab.FakeInstagram.service.interfaces.SignUpService;

@Controller
@Profile("mvc")
public class SignUpController {


    @Autowired
    private SignUpService signUpService;


    @GetMapping("/signUp")
    public String signUp(Authentication authentication, Model model) {
        if(authentication != null) {
            return "redirect:/profile";
        }else{
            model.addAttribute("userDto",new UserDto());
            return "signUp";
        }
    }


    @PostMapping(value = "/signUp", consumes = "multipart/form-data")
    public String signUp(UserDto userDto, BindingResult bindingResult, @RequestParam("file") MultipartFile multipartFile) {
        signUpService.signUp(userDto, multipartFile);
        return "to_confirm";
    }
}
