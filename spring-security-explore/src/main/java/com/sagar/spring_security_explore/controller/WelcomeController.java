package com.sagar.spring_security_explore.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("api/v1")
public class WelcomeController {

    @GetMapping({"/", "/welcome"})
    public Map<String, String> welcomeRoute() {
        Map<String, String> welcomeMsg = new HashMap<>();
        welcomeMsg.put("message", "welcome spring security explore part!");
        return welcomeMsg;
    }
}
