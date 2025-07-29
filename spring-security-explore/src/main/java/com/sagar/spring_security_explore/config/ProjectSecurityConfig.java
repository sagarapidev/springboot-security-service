package com.sagar.spring_security_explore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/v1/userProfile", "/api/v1/userSecureGroup").authenticated()
                        .requestMatchers("/api/v1/userContact", "/api/v1/userNotes", "/error").permitAll()
                )
                .formLogin(AbstractHttpConfigurer::disable) // Enables form-based login
                .logout(logout -> logout
                        .logoutUrl("/api/v1/logout") // Custom logout URL
                        .logoutSuccessUrl("/api/v1/welcome") // Redirect after logout
                        .permitAll()); // Allow everyone to access the logout URL

        return http.build();
    }
}
