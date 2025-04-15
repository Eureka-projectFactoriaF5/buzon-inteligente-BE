package com.f5.buzon_inteligente_BE.user;

import com.f5.buzon_inteligente_BE.roles.Role;
import com.f5.buzon_inteligente_BE.roles.RoleRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import org.junit.jupiter.api.BeforeEach;


import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    private Role testRole;

    @BeforeEach
    void setup() {
        testRole = roleRepository.save(new Role("ROLE_USER"));
    }

    @Test
    @DisplayName("Should find user by userId")
    void testShouldFindByUserId() {
        User user = userRepository.save(createTestUser());

        Optional<User> foundUser = userRepository.findByUserId(user.getUserId());

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUserId()).isEqualTo(user.getUserId());
        assertThat(foundUser.get().getUserName()).isEqualTo(user.getUserName());
    }

    @Test
    @DisplayName("Should find user by email")
    void testShouldFindByUserEmail() {
        User user = userRepository.save(createTestUser());

        Optional<User> foundUser = userRepository.findByUserEmail(user.getUserEmail());

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUserEmail()).isEqualTo(user.getUserEmail());
        assertThat(foundUser.get().getUserDni()).isEqualTo(user.getUserDni());
    }

    @Test
    @DisplayName("Should find user by username")
    void testShouldFindByUserName() {
        User user = userRepository.save(createTestUser());

        Optional<User> foundUser = userRepository.findByUserName(user.getUserName());

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUserName()).isEqualTo(user.getUserName());
    }

    @Test
    @DisplayName("Should find user by DNI")
    void testShouldFindByUserDni() {
        User user = userRepository.save(createTestUser());

        Optional<User> foundUser = userRepository.findByUserDni(user.getUserDni());

        assertThat(foundUser).isPresent();
        assertThat(foundUser.get().getUserDni()).isEqualTo(user.getUserDni());
    }

    @Test
    @DisplayName("Should return empty when user is not found")
    void testShouldReturnEmptyWhenUserNotFound() {
        Optional<User> user = userRepository.findByUserEmail("nonexistent@example.com");

        assertThat(user).isNotPresent();
    }

    private User createTestUser() {
        return new User(
                "12345678A",                    
                "testuser",                   
                "TestSurname",                
                "testuser@example.com",        
                "securePassword",              
                testRole                       
        );
    }
}
