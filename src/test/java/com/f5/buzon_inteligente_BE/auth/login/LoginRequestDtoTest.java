package com.f5.buzon_inteligente_BE.auth.login;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoginRequestDtoTest {
    @Test
    public void testLoginRequestDto() {
        LoginRequestDto loginRequestDto = new LoginRequestDto("email", "password");

        String email = loginRequestDto.email();
        String password = loginRequestDto.password();

        assertEquals("email", email);
        assertEquals("password", password);
    }
}
