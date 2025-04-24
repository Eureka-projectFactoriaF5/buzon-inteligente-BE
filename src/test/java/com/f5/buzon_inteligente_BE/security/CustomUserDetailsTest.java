package com.f5.buzon_inteligente_BE.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@DisplayName("CustomUserDetails Unit Tests")
public class CustomUserDetailsTest {

    private CustomUserDetails userDetails;

    @BeforeEach
    void setUp() {
        userDetails = new CustomUserDetails(1L, "DefaultUSer", "DefaultPassword", "USER", "DefaultDni", true);
    }

    @Test
    @DisplayName("Should return correct authorities with ROLE prefix")
    void testShouldGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        assertEquals(1, authorities.size());
        assertEquals("ROLE_USER", authorities.iterator().next().getAuthority());
        assertTrue(authorities.iterator().next() instanceof SimpleGrantedAuthority);
    }

    @Test
    @DisplayName("Should return correct DNI")
    void testShouldGetDni() {
        assertEquals("DefaultDni", userDetails.getDni());
    }

    @Test
    @DisplayName("Should return correct password")
    void testShouldGetPassword() {
        assertEquals("DefaultPassword", userDetails.getPassword());
    }

    @Test
    @DisplayName("Should return correct role")
    void testGetRole() {
        assertEquals("USER", userDetails.getRole());
    }

    @Test
    @DisplayName("Should return correct username")
    void testShouldGetUsername() {
        assertEquals("DefaultUSer", userDetails.getUsername());
    }

    @Test
    @DisplayName("Should return account as non-expired")
    void testShouldIsAccountNonExpired() {
        assertEquals(true, userDetails.isAccountNonExpired());
    }

    @Test
    @DisplayName("Should return account as non-locked")
    void testShouldIsAccountNonLocked() {
        assertEquals(true, userDetails.isAccountNonLocked());
    }

    @Test
    @DisplayName("Should return credentials as non-expired")
    void testShouldIsCredentialsNonExpired() {
        assertEquals(true, userDetails.isCredentialsNonExpired());
    }

    @Test
    @DisplayName("Should return account as enabled")
    void testShouldIsEnabled() {
        assertEquals(true, userDetails.isEnabled());
    }

    @Test
    @DisplayName("Should return correct userId")
    void testShouldGetUserId() {
        assertEquals(1L, userDetails.getUserId());
    }
}

