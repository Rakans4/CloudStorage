package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.User;
import com.udacity.jwdnd.course1.cloudstorage.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {
    private UserService userService;

    public SignupController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getSignupPage(User user, Model model) {
        model.addAttribute("user", user);
        return "signup";
    }

    @PostMapping
    public String postSignupPage(@ModelAttribute User user, Model model) {
        String errorMessage = null;

        if(!userService.isUsernameAvailable(user.getUsername())) {
            errorMessage = "username already exist.";
        }

        if(errorMessage == null) {
            int rowsAdded = userService.createUser(user);
            if (rowsAdded < 0) {
                errorMessage = "Error occured while signing up. Please try again";
            }
        }

        if (errorMessage == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", errorMessage);
        }

        return "signup";
    }
}
