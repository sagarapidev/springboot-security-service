package com.sagar.spring_security_explore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.ConfigurableEnvironment;

@SpringBootApplication
public class SpringSecurityExploreApplication {

    public static void main(String[] args) {
        ConfigurableEnvironment env = SpringApplication.run(SpringSecurityExploreApplication.class, args).getEnvironment();
        String port = env.getProperty("server.port", "8080");
        String contextPath = env.getProperty("server.servlet.context-path", "");
        if (contextPath.equals("/")) contextPath = "";
        String baseUrl = "http://localhost:" + port + contextPath;
        System.out.println("\n==== Useful Links ====");
        System.out.println("Swagger UI: " + baseUrl + "/swagger-ui.html");
        System.out.println("App Home:   " + baseUrl + "/");
        System.out.println("======================\n");
    }

}
