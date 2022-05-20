package com.nodomen.alertapp.controllers;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
public class ErrorController {

    @GetMapping("/endpoint")
    public String errorMessage() {
        return "simple_message";
    }
}
