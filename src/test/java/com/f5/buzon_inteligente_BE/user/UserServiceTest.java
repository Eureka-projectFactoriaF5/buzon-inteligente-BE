package com.f5.buzon_inteligente_BE.user;

import com.f5.buzon_inteligente_BE.roles.Role;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    private Role testRole;
    private User testUser;

    @BeforeEach
    void setUp() {
        testRole = new Role("ROLE_USER");
        testUser = new User(
                "12345678A",
                "testuser",
                "TestSurname",
                "test@example.com",
                "securePassword",
                testRole);
    }

    @Test
    @DisplayName("Should find user by email")
    void testFindByEmail() {
        when(userRepository.findByUserEmail("test@example.com")).thenReturn(Optional.of(testUser));

        Optional<User> result = userService.findByEmail("test@example.com");

        assertThat(result).isPresent();
        assertThat(result.get().getUserEmail()).isEqualTo("test@example.com");
        verify(userRepository).findByUserEmail("test@example.com");
    }
}   
