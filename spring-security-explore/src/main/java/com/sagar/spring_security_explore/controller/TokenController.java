package com.sagar.spring_security_explore.controller;

import com.sagar.spring_security_explore.dto.request.CredentialReqDTO;
import com.sagar.spring_security_explore.dto.request.JwtResponseDTO;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.core.env.Environment;
import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.stream.Collectors;

import static com.sagar.spring_security_explore.constant.ApplicationConstant.JWT_SECRET_KEY;
import static com.sagar.spring_security_explore.constant.ApplicationConstant.JWT_SECRET_VALUE;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/jwt")
public class TokenController {

    private final AuthenticationManager authenticationManager;
    private final Environment env; // ✅ Make this final so it's injected via constructor

    @RequestMapping("token")
    public String GenerateJwtToken() {
        return "Token will be generated if user is authenticated only!";
    }

    @PostMapping("/generate-token")
    public ResponseEntity<JwtResponseDTO> login(@RequestBody CredentialReqDTO credentials) {

        Authentication auth = new UsernamePasswordAuthenticationToken(
                credentials.username(),
                credentials.password()
        );
        authenticationManager.authenticate(auth);

        String secret = env.getProperty(JWT_SECRET_KEY, JWT_SECRET_VALUE); // ✅ Now this will work

        SecretKey secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        String jwt = Jwts.builder()
                .issuer("Sagar")
                .subject("JWT Token")
                .claim("username", auth.getName())
                .claim("authorities", auth.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.joining(",")))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 3000000)) // 50 minutes
                .signWith(secretKey)
                .compact();

        return ResponseEntity.ok(new JwtResponseDTO(jwt));
    }
}