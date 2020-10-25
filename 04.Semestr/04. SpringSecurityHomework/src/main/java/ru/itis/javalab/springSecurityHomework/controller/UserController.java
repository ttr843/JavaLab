package ru.itis.javalab.springSecurityHomework.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class UserController {

    @GetMapping("/profile")
    public String getProfile(Model model) {
        model.addAttribute("kek","hello my dear,checking freemarker model:)");
        return "profilePage";
    }
}
