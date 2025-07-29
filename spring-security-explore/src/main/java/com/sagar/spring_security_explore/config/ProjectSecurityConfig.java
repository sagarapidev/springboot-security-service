package com.sagar.spring_security_explore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.authorization.AuthenticatedAuthorizationManager.authenticated;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(requests -> requests
                .requestMatchers("/api/v1/userProfile", "/api/v1/userSecureGroup").authenticated()
                .requestMatchers("/api/v1/userContact", "/api/v1/userNotes").permitAll()
                .anyRequest().permitAll() // Allow all other requests (e.g., your WelcomeController)
            )
            .formLogin(withDefaults()) // Use withDefaults() instead of deprecated formLogin()
            .httpBasic(withDefaults());
        return http.build();
    }
}
