package com.nodomen.alertapp.controllers;

import com.nodomen.alertapp.models.Alert;
import com.nodomen.alertapp.models.User;
import com.nodomen.alertapp.repositories.AlertRepository;
import com.nodomen.alertapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;
import java.util.Collection;

@Controller
@Lazy
public class ApplicationController {

    private final UserService userService;
    private final AlertRepository alertRepository;
    private User user;
    private Alert replaceableAlert;


    @Autowired
    ApplicationController(UserService userService, AlertRepository alertRepository) {
        this.userService = userService;
        this.alertRepository = alertRepository;
        user = userService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());

    }

    @ModelAttribute("alerts")
    public Collection<Alert> alerts() {
        return alertRepository.getAllByUser(user);
    }

    @ModelAttribute("user")
    public User user() {
        return this.user;
    }

    @GetMapping("/app")
    public String invokeMainMenu(Principal principal, Model model) {
        user = userService.findByUsername(principal.getName());
        model.addAttribute("user", user);
        return "app";
    }

    @GetMapping("/app/add_alert")
    public String addAlert(Principal principal, Model model) {
        Alert alert = new Alert();
        alert.setName("Unconfirmed alert");
        alert.setText("");
        alert.setUser(user);

        alertRepository.save(alert);

        return "redirect:/app";
    }

    @GetMapping("/app/configure_alert/")
    public ModelAndView invokeAlertConfigurePage(@RequestParam("id") Long id) {

        replaceableAlert = alertRepository.getById(id);

        if (replaceableAlert.getUser().getId() != user.getId()) {
            replaceableAlert = null;

            return new ModelAndView("error");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("alert_configure");

        Alert alert = new Alert();
        modelAndView.addObject("alert", alert);

        return modelAndView;
    }

    @PostMapping("/app/configure_alert/")
    public String editAlert(@ModelAttribute("alert") Alert alertForm) {

        Alert replacingAlert = alertRepository.getById(replaceableAlert.getId());

        replacingAlert.setName(alertForm.getName());
        replacingAlert.setText(alertForm.getText());

        replacingAlert.setUser(user);

        alertRepository.deleteById(replaceableAlert.getId());
        alertRepository.save(replacingAlert);

        return "redirect:/app";
    }
}
