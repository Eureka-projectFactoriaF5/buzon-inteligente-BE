package com.f5.buzon_inteligente_BE.roles;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RoleRepository roleRepository;

    @InjectMocks
    private RoleService roleService;

    @Test
    @DisplayName("Should return role when found by ID")
    void testGetByIdSuccess() {
        Role role = new Role("ROLE_USER");
        when(roleRepository.findById(1L)).thenReturn(Optional.of(role));

        Role result = roleService.getById(1L);

        assertThat(result).isEqualTo(role);
        verify(roleRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Should throw exception when role ID not found")
    void testGetByIdNotFound() {
        when(roleRepository.findById(99L)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> roleService.getById(99L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("Role not found with ID: 99");
    }
}

