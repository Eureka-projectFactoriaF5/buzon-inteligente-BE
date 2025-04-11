package com.f5.buzon_inteligente_BE.auth.login;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/api/auth")
public class LoginController {

    private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
    private final LoginService authService;

    public LoginController(LoginService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto loginRequest) {
        try {
            LoginResponseDto response = authService.authenticate(loginRequest);
            return ResponseEntity.ok(response);
            
        } catch (AuthenticationException ex) {
            logger.error("Error de autenticación: {}", ex.getMessage());
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponseDto("Credenciales inválidas", null, null));
        } catch (IllegalArgumentException ex) {
            logger.error("Password no es Base64 válido: {}", ex.getMessage());
            return ResponseEntity.badRequest()
                    .body(new LoginResponseDto("Formato de password inválido", null, null));
        }
    }
}
