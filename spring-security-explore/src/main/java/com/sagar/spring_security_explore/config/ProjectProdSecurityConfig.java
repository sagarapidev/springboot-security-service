package com.sagar.spring_security_explore.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("prod") // Only apply this configuration in production profile
public class ProjectProdSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(requests -> requests
                        .requestMatchers("/api/admin/**").authenticated()
                        .requestMatchers(
                                "/api/customers/**",
                                "/swagger-ui.html",
                                "/swagger-ui/**",
                                "/v3/api-docs",
                                "/v3/api-docs/**",
                                "/swagger-resources/**",
                                "/webjars/**"
                        ).permitAll()
                )
                .formLogin(withDefaults())
                .httpBasic(withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .cors(corsConfig-> corsConfig
                        .configurationSource(request -> {
                            var cors = new CorsConfiguration();
                            cors.addAllowedOrigin("http://localhost:3000");
                            cors.addAllowedOrigin("http://localhost:5173");
                            cors.setAllowCredentials(true);
                            cors.addAllowedMethod("*");
                            cors.addAllowedHeader("*");
                            return cors;
                        })
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }


}
