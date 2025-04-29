package com.f5.buzon_inteligente_BE.user;

import com.f5.buzon_inteligente_BE.roles.Role;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.DataIntegrityViolationException;

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

    @Test
    @DisplayName("Should find user by ID")
    void testFindById() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(testUser));

        Optional<User> result = userService.findById(1L);

        assertThat(result).isPresent();
        assertThat(result.get()).isEqualTo(testUser);
        verify(userRepository).findById(1L);
    }

    @Test
    @DisplayName("Should save a user")
    void testSaveUser() {
        when(userRepository.save(testUser)).thenReturn(testUser);

        User saved = userService.save(testUser);

        assertThat(saved).isEqualTo(testUser);
        verify(userRepository).save(testUser);
    }

    @Test
    @DisplayName("Should return empty when user not found by email")
    void testFindByEmailNotFound() {
        when(userRepository.findByUserEmail("notfound@example.com")).thenReturn(Optional.empty());

        Optional<User> result = userService.findByEmail("notfound@example.com");

        assertThat(result).isNotPresent();
        verify(userRepository).findByUserEmail("notfound@example.com");
    }

    @Test
    @DisplayName("Should return empty when user not found by ID")
    void testFindByIdNotFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<User> result = userService.findById(99L);

        assertThat(result).isNotPresent();
        verify(userRepository).findById(99L);
    }

    @Test
    @DisplayName("Should return empty when email is null")
    void testFindByEmailWithNull() {
        when(userRepository.findByUserEmail(null)).thenReturn(Optional.empty());

        Optional<User> result = userService.findByEmail(null);

        assertThat(result).isEmpty();
        verify(userRepository).findByUserEmail(null);
    }

    @Test
    @DisplayName("Should return empty when ID is null")
    void testFindByIdWithNull() {
        when(userRepository.findById(null)).thenReturn(Optional.empty());

        Optional<User> result = userService.findById(null);

        assertThat(result).isEmpty();
        verify(userRepository).findById(null);
    }

    @Test
    @DisplayName("Should save user even with empty email if no validation is applied")
    void testSaveUserWithEmptyEmail() {
        User userWithEmptyEmail = new User(
                "12345678A",
                "testuser",
                "TestSurname",
                "",
                "securePassword",
                testRole);

        when(userRepository.save(userWithEmptyEmail)).thenReturn(userWithEmptyEmail);

        User saved = userService.save(userWithEmptyEmail);

        assertThat(saved.getUserEmail()).isEmpty();
        verify(userRepository).save(userWithEmptyEmail);
    }

    @Test
    @DisplayName("Should save user with null fields if service does not validate")
    void testSaveUserWithNullFields() {
        User userWithNullFields = new User(
                null,
                null,
                null,
                null,
                null,
                testRole);

        when(userRepository.save(userWithNullFields)).thenReturn(userWithNullFields);

        User saved = userService.save(userWithNullFields);

        assertThat(saved.getUserEmail()).isNull();
        verify(userRepository).save(userWithNullFields);
    }

    @Test
    @DisplayName("Should throw exception when trying to save a user with duplicate email")
    void testSaveDuplicateEmailThrowsException() {
        when(userRepository.save(any(User.class)))
                .thenThrow(new DataIntegrityViolationException("Email already exists"));

        assertThatThrownBy(() -> userService.save(testUser))
                .isInstanceOf(DataIntegrityViolationException.class)
                .hasMessageContaining("Email already exists");

        verify(userRepository).save(testUser);
    }

}
