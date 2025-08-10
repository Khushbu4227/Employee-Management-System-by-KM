package com.ems.controller;

import com.ems.model.User;
import com.ems.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(@ModelAttribute("user") @Valid User user,
                                BindingResult result,
                                Model model) {
        if (result.hasErrors()) {
            return "signup";
        }

        try {
            userService.registerNewUserAsUser(user);
        } catch (Exception e) {
            model.addAttribute("signupError", e.getMessage());
            return "signup";
        }

        return "redirect:/signin?registered=true";
    }

    @GetMapping("/signin")
    public String showSigninForm() {
        return "signin";
    }
}
