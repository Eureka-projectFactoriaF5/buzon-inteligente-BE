package com.f5.buzon_inteligente_BE.roles;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.test.context.ActiveProfiles;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
@EntityScan(basePackageClasses = Role.class)
@EnableJpaRepositories(basePackageClasses = RoleRepository.class)
class RoleRepositoryIntegrationTest {

    @Autowired
    private RoleRepository roleRepository;

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
    }

    @Test
    @DisplayName("Should save Role and retrieve it by ID")
    void testShouldSaveAndFindById() {
        Role saved = roleRepository.save(new Role("ROLE_USER"));
        Optional<Role> result = roleRepository.findById(saved.getRoleId());

        assertThat(result).isPresent();
        assertThat(result.get().getRoleName()).isEqualTo("ROLE_USER");
    }

    @Test
    @DisplayName("Should find Role by roleName")
    void testShouldFindByRoleName() {
        roleRepository.save(new Role("ROLE_ADMIN"));
        Optional<Role> result = roleRepository.findByRoleName("ROLE_ADMIN");

        assertThat(result).isPresent();
        assertThat(result.get().getRoleName()).isEqualTo("ROLE_ADMIN");
    }

    @Test
    @DisplayName("Should return empty when roleName not found")
    void testShouldRoleNameNotFound() {
        Optional<Role> result = roleRepository.findByRoleName("NOT_EXISTING");
        assertThat(result).isNotPresent();
    }

    @Test
    @DisplayName("Should return empty when searching with empty string")
    void testShouldEmptyRoleName() {
        Optional<Role> result = roleRepository.findByRoleName("");
        assertThat(result).isNotPresent();
    }

    @Test
    @DisplayName("Should return empty when searching with whitespace")
    void testShouldWhitespaceRoleName() {
        Optional<Role> result = roleRepository.findByRoleName("   ");
        assertThat(result).isNotPresent();
    }

    @Test
    @DisplayName("Should return empty when searching with null")
    void testShouldNullRoleName() {
        Optional<Role> result = roleRepository.findByRoleName(null);
        assertThat(result).isNotPresent();
    }

    @Test
    @DisplayName("Should treat different cases as different values")
    void testShouldCaseSensitivity() {
        roleRepository.save(new Role("ROLE_TEST"));
        Optional<Role> result = roleRepository.findByRoleName("role_test");

        assertThat(result).isNotPresent();
    }

    @Test
    @DisplayName("Should save Role with special characters in name")
    void testShouldRoleNameWithSpecialCharacters() {
        roleRepository.save(new Role("ROLE_DEV$%#"));
        Optional<Role> result = roleRepository.findByRoleName("ROLE_DEV$%#");

        assertThat(result).isPresent();
        assertThat(result.get().getRoleName()).isEqualTo("ROLE_DEV$%#");
    }

    @Test
    @DisplayName("Should save Role with trailing whitespace (not trimmed)")
    void testShouldRoleWithTrailingWhitespace() {
        roleRepository.save(new Role("ROLE_USER "));
        Optional<Role> result = roleRepository.findByRoleName("ROLE_USER ");

        assertThat(result).isPresent();
        assertThat(result.get().getRoleName()).isEqualTo("ROLE_USER ");
    }
}
