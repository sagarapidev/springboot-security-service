package com.sagar.spring_security_explore;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.sagar.spring_security_explore")
public class SpringSecurityExploreApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityExploreApplication.class, args);
    }

}
