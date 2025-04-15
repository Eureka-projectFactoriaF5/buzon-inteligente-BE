package com.f5.buzon_inteligente_BE.auth.login;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Base64;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import com.f5.buzon_inteligente_BE.security.CustomUserDetails;
import com.f5.buzon_inteligente_BE.security.JwtUtils;
@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    @InjectMocks
    private LoginService loginService;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    AuthenticationManager authenticationManager;

    @Mock
    private CustomUserDetails userDetails;

    private String email = "email";

    @Test
    @DisplayName("Should authenticate user")
    public void testAuthenticate() {
        String password = "password";
        String role = "USER";
        String dni = "12345678A";
        String encodedPassword = Base64.getEncoder().encodeToString(password.getBytes());
        LoginRequestDto request = new LoginRequestDto(email, encodedPassword);

        when(userDetails.getUsername()).thenReturn(email);
        when(userDetails.getRole()).thenReturn(role);
        when(userDetails.getDni()).thenReturn(dni);

        when(jwtUtils.generateJwtToken(email, role, dni)).thenReturn("token");

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, password);
        when(authenticationManager.authenticate(any())).thenReturn(authentication);

        LoginResponseDto response = loginService.authenticate(request);

        assertEquals("token", response.token());
        assertEquals(role, response.role());
        assertEquals("Login exitoso", response.message());

    }
    @Test
    @DisplayName("Not authenticate user whith empty password")
    public void testAuthenticateEmptyPassword() {
        String password = "";
        LoginRequestDto request = new LoginRequestDto(email, password);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> loginService.authenticate(request)
        );

        assertEquals("Password cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Not authenticate user whith null password")
    public void testAuthenticateNullPassword() {
        LoginRequestDto request = new LoginRequestDto(email, null);

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> loginService.authenticate(request)
        );

        assertEquals("Password cannot be null or empty", exception.getMessage());
    }

    @Test
    @DisplayName("Not authenticate user whith password not Base64")
    public void testAuthenticatePasswordNotBase64() {
        LoginRequestDto request = new LoginRequestDto(email, "######");

        IllegalArgumentException exception = assertThrows(
            IllegalArgumentException.class, 
            () -> loginService.authenticate(request)
        );

        assertEquals("Formato de password inválido", exception.getMessage());
    }

}
