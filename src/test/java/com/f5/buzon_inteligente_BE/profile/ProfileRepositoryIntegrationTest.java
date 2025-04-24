package com.f5.buzon_inteligente_BE.profile;

import com.f5.buzon_inteligente_BE.roles.Role;
import com.f5.buzon_inteligente_BE.roles.RoleRepository;
import com.f5.buzon_inteligente_BE.user.User;
import com.f5.buzon_inteligente_BE.user.UserRepository;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

import java.lang.reflect.Field;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles("test")
class ProfileRepositoryIntegrationTest {

    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private Role defaultRole;

    @BeforeEach
    void setUp() {
        defaultRole = createRole("USER");
    }

    @AfterEach
    void tearDown() {
        profileRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    private User createUserWithProfile(String dni, String name, String email, String credential) {
        User user = new User(dni, name, "Test", email, "test123", defaultRole);
        user = userRepository.save(user);

        Profile profile = new Profile();
        profile.setUser(user);
        profile.setPermanentCredential(credential);
        profileRepository.save(profile);

        return user;
    }

    private Role createRole(String roleName) {
        Role role = new Role();
        try {
            Field field = Role.class.getDeclaredField("roleName");
            field.setAccessible(true);
            field.set(role, roleName);
        } catch (Exception e) {
            throw new RuntimeException("Failed to set roleName via reflection", e);
        }
        return roleRepository.save(role);
    }

    @Test
    @DisplayName("Should return profile when user ID exists")
    void testShouldFindByUserUserId_shouldReturnProfile() {
        User user = createUserWithProfile("12345678A", "Pepe", "pepe@example.com", "abc123");

        Optional<Profile> result = profileRepository.findByUserUserId(user.getUserId());

        assertThat(result).isPresent();
        assertThat(result.get().getUser().getUserName()).isEqualTo("Pepe");
    }

    @Test
    @DisplayName("Should return true when profile exists for user")
    void testShouldExistsByUserUserId_shouldReturnTrue() {
        User user = createUserWithProfile("98765432B", "Carlos", "carlos@example.com", "cred456");

        boolean exists = profileRepository.existsByUserUserId(user.getUserId());

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Should return profile when credential exists")
    void testShouldFindByPermanentCredential_shouldReturnProfile() {
        createUserWithProfile("24681357C", "Ana", "ana@example.com", "unique_cred");

        Optional<Profile> result = profileRepository.findByPermanentCredential("unique_cred");

        assertThat(result).isPresent();
        assertThat(result.get().getUser().getUserName()).isEqualTo("Ana");
    }

    @Test
    @DisplayName("Should return true when credential exists")
    void testShouldExistsByPermanentCredential_shouldReturnTrue() {
        createUserWithProfile("11223344D", "Laura", "laura@example.com", "exists_cred");

        boolean exists = profileRepository.existsByPermanentCredential("exists_cred");

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Should return false when credential does not exist")
    void testShouldExistsByPermanentCredential_shouldReturnFalse() {
        boolean exists = profileRepository.existsByPermanentCredential("non_existing_cred");

        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Should return empty when userId is null")
    void testShouldFindByUserUserId_shouldReturnEmpty_whenUserIdIsNull() {
        Optional<Profile> result = profileRepository.findByUserUserId(null);
        assertThat(result).isNotPresent();
    }

    @Test
    @DisplayName("Should return empty when user does not exist")
    void testShouldFindByUserUserId_shouldReturnEmpty_whenUserDoesNotExist() {
        Optional<Profile> result = profileRepository.findByUserUserId(99999L);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should return empty when credential is null")
    void testShouldFindByPermanentCredential_shouldReturnEmpty_whenCredentialIsNull() {
        Optional<Profile> result = profileRepository.findByPermanentCredential(null);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("Should return false when credential is empty")
    void testShouldExistsByPermanentCredential_shouldReturnFalse_whenCredentialIsEmpty() {
        boolean exists = profileRepository.existsByPermanentCredential("");
        assertThat(exists).isFalse();
    }

    @Test
    @DisplayName("Should return false when userId is negative")
    void testShouldExistsByUserUserId_shouldReturnFalse_whenUserIdIsNegative() {
        boolean exists = profileRepository.existsByUserUserId(-1L);
        assertThat(exists).isFalse();
    }

}
