package com.nodomen.alertapp.controllers;

import com.nodomen.alertapp.models.User;
import com.nodomen.alertapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class RegistrationController {

    PasswordEncoder passwordEncoder;
    UserRepository repository;

    @Autowired
    public void setEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Autowired
    public void setUserRepository(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/registration")
    public String registrationPage(Model model) {

        model.addAttribute("user", new User());

        return "registration";
    }


    @PostMapping("/registration")
    public String registerUser(@ModelAttribute("user") User user) {

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        repository.save(user);

        return "redirect:/login";
    }
}
