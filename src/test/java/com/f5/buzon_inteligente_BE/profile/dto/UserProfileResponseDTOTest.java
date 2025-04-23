package com.f5.buzon_inteligente_BE.profile.dto;

import com.f5.buzon_inteligente_BE.profile.Profile;
import com.f5.buzon_inteligente_BE.profile.DTO.UserProfileResponseDTO;
import com.f5.buzon_inteligente_BE.roles.Role;
import com.f5.buzon_inteligente_BE.user.User;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class UserProfileResponseDTOTest {

    private User user;
    private Profile profile;

    @BeforeEach
    void setUp() {
        Role dummyRole = new Role();
        user = new User(
                "99999999B",
                "Ana",
                "López",
                "ana@example.com",
                "password123",
                dummyRole);

        profile = new Profile();
        profile.setPermanentCredential("cred456");
    }

    @Test
    @DisplayName("Should create object with null fields when using empty constructor")
    void testShouldEmptyConstructor_shouldCreateObjectWithNullFields() {
        UserProfileResponseDTO dto = new UserProfileResponseDTO();

        assertThat(dto.getUserDni()).isNull();
        assertThat(dto.getUserName()).isNull();
        assertThat(dto.getUserSurname()).isNull();
        assertThat(dto.getUserEmail()).isNull();
        assertThat(dto.getPermanentCredential()).isNull();
    }

    @Test
    @DisplayName("Should correctly set all fields using constructor with arguments")
    void testShouldConstructorWithArguments_shouldSetAllFields() {
        UserProfileResponseDTO dto = new UserProfileResponseDTO(
                "12345678A", "Juan", "Pérez", "juan@example.com", "abc123");

        assertThat(dto.getUserDni()).isEqualTo("12345678A");
        assertThat(dto.getUserName()).isEqualTo("Juan");
        assertThat(dto.getUserSurname()).isEqualTo("Pérez");
        assertThat(dto.getUserEmail()).isEqualTo("juan@example.com");
        assertThat(dto.getPermanentCredential()).isEqualTo("abc123");
    }

    @Test
    @DisplayName("Should map data correctly from User and Profile in fromEntities")
    void testShouldFromEntities_shouldMapCorrectly() {
        UserProfileResponseDTO dto = UserProfileResponseDTO.fromEntities(user, profile);

        assertThat(dto.getUserDni()).isEqualTo("99999999B");
        assertThat(dto.getUserName()).isEqualTo("Ana");
        assertThat(dto.getUserSurname()).isEqualTo("López");
        assertThat(dto.getUserEmail()).isEqualTo("ana@example.com");
        assertThat(dto.getPermanentCredential()).isEqualTo("cred456");
    }

    @Test
    @DisplayName("Should allow updating fields using setters")
    void testShouldSetters_shouldUpdateFields() {
        UserProfileResponseDTO dto = new UserProfileResponseDTO();
        dto.setUserDni("11111111C");
        dto.setUserName("Carlos");
        dto.setUserSurname("Martín");
        dto.setUserEmail("carlos@example.com");

        assertThat(dto.getUserDni()).isEqualTo("11111111C");
        assertThat(dto.getUserName()).isEqualTo("Carlos");
        assertThat(dto.getUserSurname()).isEqualTo("Martín");
        assertThat(dto.getUserEmail()).isEqualTo("carlos@example.com");
    }

    @Test
    @DisplayName("Should throw NullPointerException when user is null in fromEntities")
    void testShouldFromEntities_withNullUser_shouldThrowException() {
        Profile profile = new Profile();
        profile.setPermanentCredential("cred999");

        assertThatThrownBy(() -> UserProfileResponseDTO.fromEntities(null, profile))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Should throw NullPointerException when profile is null in fromEntities")
    void testShouldFromEntities_withNullProfile_shouldThrowException() {
        Role dummyRole = new Role();
        User user = new User("12345678A", "Pepe", "López", "pepe@example.com", "pass", dummyRole);

        assertThatThrownBy(() -> UserProfileResponseDTO.fromEntities(user, null))
                .isInstanceOf(NullPointerException.class);
    }

    @Test
    @DisplayName("Should handle empty strings in constructor without errors")
    void testShouldConstructorWithEmptyStrings_shouldWork() {
        UserProfileResponseDTO dto = new UserProfileResponseDTO("", "", "", "", "");

        assertThat(dto.getUserDni()).isEmpty();
        assertThat(dto.getUserName()).isEmpty();
        assertThat(dto.getUserSurname()).isEmpty();
        assertThat(dto.getUserEmail()).isEmpty();
        assertThat(dto.getPermanentCredential()).isEmpty();
    }

}
