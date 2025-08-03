package com.polarbookshop.catalogservice.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @Value("${polar.greetings}")
    private String greetings;

    @GetMapping("/")
    public String getGreetings()
    {
        return greetings;
    }
}
