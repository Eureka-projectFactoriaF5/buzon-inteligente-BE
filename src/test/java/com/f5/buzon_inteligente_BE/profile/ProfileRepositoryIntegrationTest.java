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
    void testShouldfindByUserUserId_shouldReturnProfile() {
        User user = createUserWithProfile("12345678A", "Pepe", "pepe@example.com", "abc123");

        Optional<Profile> result = profileRepository.findByUserUserId(user.getUserId());

        assertThat(result).isPresent();
        assertThat(result.get().getUser().getUserName()).isEqualTo("Pepe");
    }

    @Test
    @DisplayName("Should return true when profile exists for user")
    void testShouldexistsByUserUserId_shouldReturnTrue() {
        User user = createUserWithProfile("98765432B", "Carlos", "carlos@example.com", "cred456");

        boolean exists = profileRepository.existsByUserUserId(user.getUserId());

        assertThat(exists).isTrue();
    }

    @Test
    @DisplayName("Should return profile when credential exists")
    void testShouldfindByPermanentCredential_shouldReturnProfile() {
        createUserWithProfile("24681357C", "Ana", "ana@example.com", "unique_cred");

        Optional<Profile> result = profileRepository.findByPermanentCredential("unique_cred");

        assertThat(result).isPresent();
        assertThat(result.get().getUser().getUserName()).isEqualTo("Ana");
    }

}
