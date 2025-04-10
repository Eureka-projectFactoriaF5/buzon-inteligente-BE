package com.f5.buzon_inteligente_BE.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomUserDetailsTest {

    private CustomUserDetails userDetails;
    @BeforeEach
    void setUp() {
        userDetails = new CustomUserDetails("DefaultUSer", "DefaultPassword", "USER", "DefaultDni", true);
    }
    @Test
    void testGetAuthorities() {
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();

        assertEquals(1, authorities.size());
        assertEquals("ROLE_USER", authorities.iterator().next().getAuthority());
        assertTrue(authorities.iterator().next() instanceof SimpleGrantedAuthority);

    }

    @Test
    void testGetDni() {
        assertEquals("DefaultDni", userDetails.getDni());
    }

    @Test
    void testGetPassword() {
        assertEquals("DefaultPassword", userDetails.getPassword());   
    }

    @Test
    void testGetRole() {
        assertEquals("USER", userDetails.getRole());
    }

    @Test
    void testGetUsername() {
        assertEquals("DefaultUSer", userDetails.getUsername());
    }

    @Test
    void testIsAccountNonExpired() {
        assertEquals(true, userDetails.isAccountNonExpired());
    }

    @Test
    void testIsAccountNonLocked() {
        assertEquals(true, userDetails.isAccountNonLocked());
    }

    @Test
    void testIsCredentialsNonExpired() {
        assertEquals(true, userDetails.isCredentialsNonExpired());
    }

    @Test
    void testIsEnabled() {
        assertEquals(true, userDetails.isEnabled());
    }
}
