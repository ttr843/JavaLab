package ru.itis.javalab.FakeInstagram.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RootController {


    @GetMapping("/")
    public String rootControl(Authentication authentication) {
        if(authentication != null) {
            return "redirect:/profile";
        }else{
            return "redirect:/login";
        }
    }
}
