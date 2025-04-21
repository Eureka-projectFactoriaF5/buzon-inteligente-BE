package com.f5.buzon_inteligente_BE.profile;

import com.f5.buzon_inteligente_BE.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ProfileTest {

    private Profile profile;

    @BeforeEach
    void setup() {
        profile = new Profile();
    }

    @Test
    @DisplayName("Should generate permanentCredential with 8 alphanumeric characters by default")
    void testDefaultConstructorGeneratesCredential() {
        assertThat(profile.getPermanentCredential())
                .isNotNull()
                .hasSize(8)
                .matches("^[a-zA-Z0-9]+$");
    }

    @Test
    @DisplayName("Should allow setting and getting ID")
    void testSetAndGetId() {
        profile.setId(100L);
        assertThat(profile.getId()).isEqualTo(100L);
    }

    @Test
    @DisplayName("Should allow setting and getting permanentCredential")
    void testSetAndGetCredential() {
        profile.setPermanentCredential("ABCD1234");
        assertThat(profile.getPermanentCredential()).isEqualTo("ABCD1234");
    }

    @Test
    @DisplayName("Should set user via constructor and keep permanentCredential")
    void testConstructorWithUser() {
        User mockUser = new User();
        Profile profileWithUser = new Profile(mockUser);

        assertThat(profileWithUser.getUser()).isEqualTo(mockUser);
        assertThat(profileWithUser.getPermanentCredential())
                .isNotNull()
                .hasSize(8);
    }

    @Test
    @DisplayName("Should allow setting and getting user manually")
    void testSetAndGetUser() {
        User mockUser = new User();
        profile.setUser(mockUser);

        assertThat(profile.getUser()).isEqualTo(mockUser);
    }

    @Test
    @DisplayName("Should allow setting null user")
    void testAllowNullUser() {
        profile.setUser(null);
        assertThat(profile.getUser()).isNull();
    }

    @Test
    @DisplayName("Should allow setting null credential")
    void testAllowNullCredential() {
        profile.setPermanentCredential(null);
        assertThat(profile.getPermanentCredential()).isNull();
    }

    @Test
    @DisplayName("Should allow changing the permanentCredential")
    void testCredentialIsMutable() {
        profile.setPermanentCredential("NEWCODE01");
        assertThat(profile.getPermanentCredential()).isEqualTo("NEWCODE01");
    }

    @Test
    @DisplayName("Should generate unique permanentCredentials in multiple instances")
    void testUniquePermanentCredential() {
        Profile p1 = new Profile();
        Profile p2 = new Profile();

        assertThat(p1.getPermanentCredential())
                .isNotEqualTo(p2.getPermanentCredential());
    }

}
