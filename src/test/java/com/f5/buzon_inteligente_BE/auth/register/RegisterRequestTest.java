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
        registerRequestEmpty.setUserDni("87654321");
        assertEquals("87654321", registerRequestEmpty.getUserDni());
        registerRequestEmpty.setUserDni("12345678");
        assertEquals("12345678", registerRequestEmpty.getUserDni());
    }

    @Test
    void testSetUserEmail() {
        registerRequestEmpty.setUserEmail("example@email");
        assertEquals("example@email", registerRequestEmpty.getUserEmail());
        registerRequestEmpty.setUserEmail("email@example.com");
        assertEquals("email@example.com", registerRequestEmpty.getUserEmail());
    }

    @Test
    void testSetUserName() {
        registerRequestEmpty.setUserName("newName");
        assertEquals("newName", registerRequestEmpty.getUserName());
        registerRequestEmpty.setUserName("Name");
        assertEquals("Name", registerRequestEmpty.getUserName());
    }

    @Test
    void testSetUserPassword() {
        registerRequestEmpty.setUserPassword("newPassword");
        assertEquals("newPassword", registerRequestEmpty.getUserPassword());
        registerRequestEmpty.setUserPassword("Password");
        assertEquals("Password", registerRequestEmpty.getUserPassword());
    }

    @Test
    void testSetUserSurname() {
        registerRequestEmpty.setUserSurname("newSurname");
        assertEquals("newSurname", registerRequestEmpty.getUserSurname());
        registerRequestEmpty.setUserSurname("Surname");
        assertEquals("Surname", registerRequestEmpty.getUserSurname());
    }

}
