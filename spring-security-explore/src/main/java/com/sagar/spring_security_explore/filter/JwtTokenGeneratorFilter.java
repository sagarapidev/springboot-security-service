package com.sagar.spring_security_explore.filter;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import static com.sagar.spring_security_explore.constant.ApplicationConstant.*;

@Slf4j
public class JwtTokenGeneratorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("JwtTokenGeneratorFilter called");
        org.springframework.security.core.Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null && auth.isAuthenticated()) {
            log.info("User is authenticated, generating token");
            Environment env = getEnvironment();
            String secret = env.getProperty(JWT_SECRET_KEY, JWT_SECRET_VALUE);
            SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
           String jwt= Jwts.builder()
                    .issuer("Sagar")
                    .subject("JWT Token")
                    .claim("username", auth.getName())
                    .claim("authorities", auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(",")))
                    .issuedAt(new Date())
                    .expiration(new Date(new Date().getTime() + 3000000)) // 50 minutes
                    .signWith(secretKey)
                    .compact();

            response.setHeader(JWT_HEADER, jwt);
            log.info("JWT Token generated and added to response header");
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        log.info("In shouldNotFilter method");
        return !request.getServletPath().equals("/api/jwt/token");
    }
}
