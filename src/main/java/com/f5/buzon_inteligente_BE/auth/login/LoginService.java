package com.f5.buzon_inteligente_BE.auth.login;

import java.util.Base64;
import java.util.Base64.Decoder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.f5.buzon_inteligente_BE.security.CustomUserDetails;
import com.f5.buzon_inteligente_BE.security.JwtUtils;

@Service
public class LoginService {

    private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    
    public LoginService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }
    
    public LoginResponseDto authenticate(LoginRequestDto loginRequest) {
        logger.info("Received login request for email: {}", loginRequest.email());
        
        
        if (loginRequest.password() == null || loginRequest.password().trim().isEmpty()) {
            logger.error("Password is null or empty for email: {}", loginRequest.email());
            throw new IllegalArgumentException("Password cannot be null or empty");
        }
        
        String decodedPassword;
        try {
            Decoder decoder = Base64.getDecoder();
            byte[] decodedBytes = decoder.decode(loginRequest.password());
            decodedPassword = new String(decodedBytes);
            logger.debug("Decoded password for email {}: {}", loginRequest.email(), decodedPassword);
        } catch (Exception e) {
            logger.error("Error decoding password from Base64 for email {}: {}", loginRequest.email(), e.getMessage());
            throw new IllegalArgumentException("Formato de password inválido", e);
        }
        

        Authentication auth = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.email(), decodedPassword)
        );
        
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String role = userDetails.getRole();
        String dni = userDetails.getDni();
        
        logger.info("Authentication successful for email: {} with role: {}", loginRequest.email(), role);
        
        String token = jwtUtils.generateJwtToken(userDetails.getUsername(), role, dni, userDetails.getUserId());
        logger.debug("Generated token: {}", token);
        
        return new LoginResponseDto("Login exitoso", token, role);
    }
}
