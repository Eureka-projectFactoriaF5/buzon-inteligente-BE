package com.f5.buzon_inteligente_BE.roles;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RoleTest {

    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role("ROLE_USER");
    }

    @Test
    @DisplayName("Should create Role with name via constructor")
    void testRoleConstructor() {
        assertThat(role.getRoleName()).isEqualTo("ROLE_USER");
    }

    @Test
    @DisplayName("Should allow default constructor for JPA")
    void testDefaultConstructor() {
        Role emptyRole = new Role();
        assertThat(emptyRole).isNotNull();
    }

    @Test
    @DisplayName("Should return null for ID before persistence")
    void testRoleIdInitiallyNull() {
        assertThat(role.getRoleId()).isNull();
    }   

}
