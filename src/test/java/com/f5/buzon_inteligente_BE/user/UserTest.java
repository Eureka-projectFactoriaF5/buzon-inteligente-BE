package com.f5.buzon_inteligente_BE.user;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.f5.buzon_inteligente_BE.model.User;

public class UserTest {
    
    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "12345678", "John Doe", "x0N0e@example.com", "password", 1L, 1L);
    }

    @Test
    @DisplayName("GetCredentialId test case")
    void testGetCredentialId() {
        assertEquals(1L, user.getCredentialId());        
    }

    @Test
    @DisplayName("GetLockerId test case")
    void testGetLockerId() {
        assertEquals(1L, user.getLockerId());
    }

    @Test
    @DisplayName("GetUserDni test case")
    void testGetUserDni() {
        assertEquals("12345678", user.getUserDni());
    }

    @Test
    @DisplayName("GetUserEmail test case")
    void testGetUserEmail() {
        assertEquals("x0N0e@example.com", user.getUserEmail());
    }

    @Test
    @DisplayName("GetUserId test case")
    void testGetUserId() {
        assertEquals(1L, user.getUserId());
    }

    @Test
    @DisplayName("GetUserName test case")
    void testGetUserName() {
        assertEquals("John Doe", user.getUserName());

    }

    @Test
    @DisplayName("GetUserPassword test case")
    void testGetUserPassword() {
        assertEquals("password", user.getUserPassword());
    }
}
