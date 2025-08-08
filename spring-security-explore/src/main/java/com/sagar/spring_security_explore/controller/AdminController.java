package com.sagar.spring_security_explore.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    // Define admin-specific endpoints here
    // For example, you might have methods to manage users, roles, etc.

    // Example endpoint
    @RequestMapping("/dashboard")
    public String adminDashboard() {
        return "Welcome to the Admin Dashboard!";
    }

    // Add more admin-related methods as needed
}
