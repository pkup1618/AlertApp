package com.nodomen.alertapp.controllers;

import com.nodomen.alertapp.models.User;
import com.nodomen.alertapp.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class MainMenuController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/main_menu")
    public String mainPage(Principal principal) {
        User user = userService.findByUsername(principal.getName());
        String message = "hello, my new authenticated user" + user.getUsername() + "\n" + user.getEmail();
        return message;
    }
}
