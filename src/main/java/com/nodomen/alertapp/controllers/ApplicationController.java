package com.nodomen.alertapp.controllers;

import com.nodomen.alertapp.models.User;
import com.nodomen.alertapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ApplicationController {

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/app")
    public String invokeMainMenu(Principal principal) {

        User user = userService.findByUsername(principal.getName());

        String message = "Здравствуйте, " + user.getUsername() + "\n" + user.getEmail();

        return message;
    }

}
