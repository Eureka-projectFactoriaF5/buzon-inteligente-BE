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

    @Test
    @DisplayName("Should allow null roleName in constructor ")
    void testConstructorWithNullRoleName() {
        Role nullRole = new Role(null);
        assertThat(nullRole.getRoleName()).isNull();
    }

    @Test
    @DisplayName("Should allow empty string as roleName")
    void testConstructorWithEmptyRoleName() {
        Role emptyNameRole = new Role("");
        assertThat(emptyNameRole.getRoleName()).isEmpty();
    }

    @Test
    @DisplayName("Should allow roleName with exactly 50 characters (boundary test)")
    void testRoleNameWithMaxLength() {
        String longName = "R".repeat(50);
        Role longNameRole = new Role(longName);
        assertThat(longNameRole.getRoleName()).hasSize(50);
    }

    @Test
    @DisplayName("Should accept roleName longer than 50 characters (but may fail on persistence)")
    void testRoleNameExceedingMaxLength() {
        String tooLong = "R".repeat(60);
        Role tooLongRole = new Role(tooLong);
        assertThat(tooLongRole.getRoleName()).hasSize(60);
    }



}
