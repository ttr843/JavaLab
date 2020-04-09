package ru.itis.javalab.FakeInstagram.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.itis.javalab.FakeInstagram.service.ConfirmService;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ConfirmController {


    @Autowired
    private ConfirmService confirmService;


    @GetMapping("/confirm")
    public String confirm(Model model, @RequestParam String confirmCode, Authentication authentication) {
        if(authentication != null) {
            return "redirect:/profile";
        }else{
            boolean isConfirmed = confirmService.confirm(confirmCode);
            model.addAttribute("isConfirmed", isConfirmed);
            return "confirm";
        }
    }
}
