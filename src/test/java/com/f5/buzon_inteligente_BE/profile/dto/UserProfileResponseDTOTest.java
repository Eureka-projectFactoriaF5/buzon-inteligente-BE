package com.f5.buzon_inteligente_BE.profile.dto;

import com.f5.buzon_inteligente_BE.profile.Profile;
import com.f5.buzon_inteligente_BE.profile.DTO.UserProfileResponseDTO;
import com.f5.buzon_inteligente_BE.roles.Role;
import com.f5.buzon_inteligente_BE.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

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
                dummyRole
        );

        profile = new Profile();
        profile.setPermanentCredential("cred456");
    }

    @Test
    @DisplayName("Constructor vacío debe crear objeto con campos null")
    void emptyConstructor_shouldCreateObjectWithNullFields() {
        UserProfileResponseDTO dto = new UserProfileResponseDTO();

        assertThat(dto.getUserDni()).isNull();
        assertThat(dto.getUserName()).isNull();
        assertThat(dto.getUserSurname()).isNull();
        assertThat(dto.getUserEmail()).isNull();
        assertThat(dto.getPermanentCredential()).isNull();
    }

    @Test
    @DisplayName("Constructor con argumentos debe establecer correctamente los campos")
    void constructorWithArguments_shouldSetAllFields() {
        UserProfileResponseDTO dto = new UserProfileResponseDTO(
                "12345678A", "Juan", "Pérez", "juan@example.com", "abc123"
        );

        assertThat(dto.getUserDni()).isEqualTo("12345678A");
        assertThat(dto.getUserName()).isEqualTo("Juan");
        assertThat(dto.getUserSurname()).isEqualTo("Pérez");
        assertThat(dto.getUserEmail()).isEqualTo("juan@example.com");
        assertThat(dto.getPermanentCredential()).isEqualTo("abc123");
    }

    @Test
    @DisplayName("fromEntities debe mapear correctamente los datos desde User y Profile")
    void fromEntities_shouldMapCorrectly() {
        UserProfileResponseDTO dto = UserProfileResponseDTO.fromEntities(user, profile);

        assertThat(dto.getUserDni()).isEqualTo("99999999B");
        assertThat(dto.getUserName()).isEqualTo("Ana");
        assertThat(dto.getUserSurname()).isEqualTo("López");
        assertThat(dto.getUserEmail()).isEqualTo("ana@example.com");
        assertThat(dto.getPermanentCredential()).isEqualTo("cred456");
    }

    @Test
    @DisplayName("Setters deben permitir actualizar los valores de los campos")
    void setters_shouldUpdateFields() {
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
}


