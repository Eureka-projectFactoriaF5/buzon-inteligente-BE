package com.f5.buzon_inteligente_BE.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;

import com.f5.buzon_inteligente_BE.roles.Role;


public class UserTest {
    
    private User user;
    private Role role;

    @BeforeEach
    void setUp() throws Exception{
        role = new Role("USER");
        user = new User("12345678Y", "John", "Doe", "x0N0e@example.com", "password", role);
        Field userIdField = User.class.getDeclaredField("userId");
        userIdField.setAccessible(true);
        userIdField.set(user, 1L);
    }

    @Test
    @DisplayName("GetUserId test case")
    void testGetUserId() {
        assertEquals(1L, user.getUserId());
    }

    @Test
    @DisplayName("GetUserDni test case")
    void testGetUserDni() {
        assertEquals("12345678Y", user.getUserDni());
    }

    @Test
    @DisplayName("GetUserEmail test case")
    void testGetUserEmail() {
        assertEquals("x0N0e@example.com", user.getUserEmail());
    }

    @Test
    @DisplayName("GetUserPassword test case")
    void testGetUserPassword() {
        assertEquals("password", user.getUserPassword());
    }

    @Test
    @DisplayName("GetUserName test case")
    void testGetUserName() {
        assertEquals("John", user.getUserName());

    }
    @Test
    @DisplayName("GetUserSurname test case")
    void testGetUserSurname() {
        assertEquals("Doe", user.getUserSurname());
    }

    @Test
    @DisplayName("GetRole test case")
    void testGetRole() {
        assertEquals(role, user.getRole());
    }
}
