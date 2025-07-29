package com.sagar.spring_security_explore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
public class UserController {
    @RequestMapping("/userProfile")
    public String userProfile() {
        return "User Profile";
    }

    @RequestMapping("/userContact")
    public String userContact() {
        return "User Contact";
    }

    @RequestMapping("/userNotes")
    public String userNotes() {
        return "User Notes";
    }

    @RequestMapping("/userSecureGroup")
    public String userSecureGroup() {
        return "User Secure Group";
    }
}
