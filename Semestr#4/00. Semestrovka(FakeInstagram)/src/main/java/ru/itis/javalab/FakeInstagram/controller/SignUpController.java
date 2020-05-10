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
import ru.itis.javalab.FakeInstagram.form.ProfileForm;
import ru.itis.javalab.FakeInstagram.model.User;
import ru.itis.javalab.FakeInstagram.service.interfaces.SignUpService;

import javax.validation.Valid;

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
            model.addAttribute("profileForm", new ProfileForm());
            return "signUp";
        }
    }


    @PostMapping(value = "/signUp")
    public String signUp(@Valid @ModelAttribute("profileForm")ProfileForm form, BindingResult bindingResult, Model model) {
        System.out.println(form);
        System.out.println(bindingResult.getAllErrors());
        if(bindingResult.hasErrors()) {
            model.addAttribute("profileForm", form);
            return "signUp";
        }else {
            UserDto userDto = UserDto.builder()
                    .name(form.getName())
                    .surname(form.getSurname())
                    .email(form.getEmail())
                    .password(form.getPassword())
                    .build();
            signUpService.signUp(userDto);
            return "to_confirm";
        }
    }
}
