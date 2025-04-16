package com.f5.buzon_inteligente_BE.auth.register;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegisterRequestTest {

    RegisterRequest registerRequest;
    RegisterRequest registerRequestEmpty;
    @BeforeEach
    void setUp() {
        registerRequest = new RegisterRequest("12345678", "name", "surname", "email@example.com", "password");
        registerRequestEmpty = new RegisterRequest();
    }
    @Test
    void testGetUserDni() {
        assertEquals("12345678", registerRequest.getUserDni());
    }

    @Test
    void testGetUserEmail() {
        assertEquals("email@example.com", registerRequest.getUserEmail());
    }

    @Test
    void testGetUserName() {
        assertEquals("name", registerRequest.getUserName());
    }

    @Test
    void testGetUserPassword() {
        assertEquals("password", registerRequest.getUserPassword());
    }

    @Test
    void testGetUserSurname() {
        assertEquals("surname", registerRequest.getUserSurname());
    }

    @Test
    void testSetUserDni() {
        registerRequest.setUserDni("87654321");
        assertEquals("87654321", registerRequest.getUserDni());
    }

    @Test
    void testSetUserEmail() {
        registerRequest.setUserEmail("example@email");
        assertEquals("example@email", registerRequest.getUserEmail());
    }

    @Test
    void testSetUserName() {
        registerRequest.setUserName("newName");
        assertEquals("newName", registerRequest.getUserName());
    }

    @Test
    void testSetUserPassword() {
        registerRequest.setUserPassword("newPassword");
        assertEquals("newPassword", registerRequest.getUserPassword());
    }

    @Test
    void testSetUserSurname() {
        registerRequest.setUserSurname("newSurname");
        assertEquals("newSurname", registerRequest.getUserSurname());
    }

}
