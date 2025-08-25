package com.sagar.spring_security_explore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/jwt")
public class TokenController {
    @RequestMapping("token")
    public String GenerateJwtToken() {
        return "Token will be generated if user is authenticated only!";
    }
}
