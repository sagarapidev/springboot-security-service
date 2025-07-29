package com.sagar.spring_security_explore.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/v1/userProfile", "/api/v1/userSecureGroup").authenticated()
                        .requestMatchers("/api/v1/userContact", "/api/v1/userNotes", "/error").permitAll()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults());

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails admin =
                User.withUsername("admin")
                        .password("admin")
                        .authorities("admin")
                        .build();
        UserDetails user =User.withUsername("user")
                        .password("user")
                        .authorities("read")
                        .build();

        return new InMemoryUserDetailsManager(admin, user);
    }
}
