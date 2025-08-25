package com.sagar.spring_security_explore.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.env.Environment;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import static com.sagar.spring_security_explore.constant.ApplicationConstant.*;

@Slf4j
public class JwtTokenValidatorFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        log.info("Jwt token validator filter called");
        String headerJwtToken = request.getHeader(JWT_HEADER);
        if (headerJwtToken != null) {
            try {
                Environment env = getEnvironment();
                String secret = env.getProperty(JWT_SECRET_KEY, JWT_SECRET_VALUE);
                SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
                Claims claims = Jwts.parser().verifyWith(secretKey).build().parseSignedClaims(headerJwtToken).getPayload();
                String username = claims.get("username", String.class);
                String authorities = claims.get("authorities", String.class);

                Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, AuthorityUtils.commaSeparatedStringToAuthorityList(authorities));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (Exception e) {
                throw new BadCredentialsException("Invalid token received");
            }
        }
        filterChain.doFilter(request, response);
    }

    @Override
    protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
        log.info("In should not filter method");
        return request.getServletPath().equals("/api/jwt/token");
    }
}
