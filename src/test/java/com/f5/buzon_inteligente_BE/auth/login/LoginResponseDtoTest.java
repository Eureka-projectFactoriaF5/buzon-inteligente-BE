package com.f5.buzon_inteligente_BE.auth.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginResponseDtoTest {
    @Test
    public void testLoginResponseDto() {
        LoginResponseDto loginResponseDto = new LoginResponseDto("message", "token", "role");

        String message = loginResponseDto.message();
        String token = loginResponseDto.token();
        String role = loginResponseDto.role();

        assertEquals("message", message);
        assertEquals("token", token);
        assertEquals("role", role);
    }
}
