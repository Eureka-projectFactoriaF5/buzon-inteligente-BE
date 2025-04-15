package com.f5.buzon_inteligente_BE.auth.login;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Base64;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

import com.f5.buzon_inteligente_BE.security.CustomUserDetails;
import com.f5.buzon_inteligente_BE.security.CustomUserDetailsService;
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

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private Authentication auth;

    @Test
    public void testAuthenticate() {
        String email = "email";
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

    }

}
