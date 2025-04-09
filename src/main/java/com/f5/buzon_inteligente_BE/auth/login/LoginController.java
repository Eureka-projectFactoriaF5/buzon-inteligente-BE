package com.f5.buzon_inteligente_BE.auth.login;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.f5.buzon_inteligente_BE.security.JwtUtils;

@RestController
@RequestMapping("${api-endpoint}/auth")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;

    public LoginController(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        try {
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

            return ResponseEntity.ok(new LoginResponse("Login exitoso", token));
            
        }catch (AuthenticationException ex) {
            logger.error("Error de autenticación: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse("Credenciales inválidas", null));
        } catch (IllegalArgumentException ex) {
            logger.error("Password no es Base64 válido: {}", ex.getMessage());
            return ResponseEntity.badRequest()
                    .body(new LoginResponse("Formato de password inválido", null));
        }
    }
}
