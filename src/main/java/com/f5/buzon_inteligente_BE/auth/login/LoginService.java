package com.f5.buzon_inteligente_BE.auth.login;

import java.util.Base64;


import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.f5.buzon_inteligente_BE.security.CustomUserDetails;
import com.f5.buzon_inteligente_BE.security.JwtUtils;

@Service
public class LoginService {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    
    public LoginService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }
    
    public LoginResponseDto authenticate(LoginRequestDto loginRequest) {

        byte[] decodedBytes = Base64.getDecoder().decode(loginRequest.password());
        String decodedPassword = new String(decodedBytes);
        

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                    loginRequest.mail(), 
                    decodedPassword
                )
            );
        
        CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
        String role = userDetails.getRole();
        String dni = userDetails.getDni();
        
        String token = jwtUtils.generateJwtToken(userDetails.getUsername(), role, dni);
        return new LoginResponseDto("Login exitoso", token, role);
    }
}