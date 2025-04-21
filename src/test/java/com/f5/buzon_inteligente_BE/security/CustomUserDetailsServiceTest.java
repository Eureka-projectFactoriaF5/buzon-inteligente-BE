package com.f5.buzon_inteligente_BE.security;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.f5.buzon_inteligente_BE.user.User;
import com.f5.buzon_inteligente_BE.user.UserRepository;
import com.f5.buzon_inteligente_BE.roles.Role;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Mock 
    private UserRepository userRepository;

    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;


    @Test
    @DisplayName("Test load user by email")
    void testLoadUserByEmail() {
        Role role = new Role("USER");
        User user = new User("12345678X", "test", "example", "test@example.com", "password123", role);

        when(userRepository.findByUserEmail("test@example.com")).thenReturn(Optional.of(user));

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("test@example.com");
        
        assertEquals("test@example.com", userDetails.getUsername());
        assertEquals("password123", userDetails.getPassword());
        assertTrue(userDetails.isEnabled());
    }
    
    @Test
    @DisplayName("Test load user by email don't exist")
    void testLoadUserByEmailNotExist() {
        String email = "notfound@example.com";
        when(userRepository.findByUserEmail(email)).thenReturn(Optional.empty());
        UsernameNotFoundException exception = assertThrows(
            UsernameNotFoundException.class,
            () -> customUserDetailsService.loadUserByUsername(email)
        );

        assertEquals("Usuario no encontrado: " + email, exception.getMessage());
    }
}
