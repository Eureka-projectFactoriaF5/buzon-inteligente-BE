package com.f5.buzon_inteligente_BE.accesscode;

import com.f5.buzon_inteligente_BE.parcel.Parcel;
import com.f5.buzon_inteligente_BE.profile.Profile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class AccessCodeTest {

    private Profile profile;
    private AccessCodeStatus status;
    private AccessCode accessCode;

    @BeforeEach
    void setUp() {
        profile = new Profile();
        status = new AccessCodeStatus();
        accessCode = new AccessCode(
                "AC123",
                "Main Door",
                profile,
                status,
                LocalDateTime.now(),
                false);
    }

    @Test
    @DisplayName("Should set all fields correctly using constructor")
    void testShouldSetAllFieldsUsingConstructor() {
        assertThat(accessCode.getAccessCode()).isEqualTo("AC123");
        assertThat(accessCode.getAccessCodeName()).isEqualTo("Main Door");
        assertThat(accessCode.getProfile()).isEqualTo(profile);
        assertThat(accessCode.getAccessCodeStatus()).isEqualTo(status);
        assertThat(accessCode.isLocked()).isFalse();
        assertThat(accessCode.getUpdateOn()).isNotNull();
    }

    @Test
    @DisplayName("Should update access code name with setter")
    void testShouldUpdateAccessCodeName() {
        accessCode.setAccessCodeName("Back Door");
        assertThat(accessCode.getAccessCodeName()).isEqualTo("Back Door");
    }

    @Test
    @DisplayName("Should update access code status and update timestamp")
    void testShouldUpdateAccessCodeStatus() {
        AccessCodeStatus newStatus = new AccessCodeStatus();
        accessCode.setAccessCodeStatus(newStatus);
        assertThat(accessCode.getAccessCodeStatus()).isEqualTo(newStatus);
        assertThat(accessCode.getUpdateOn()).isNotNull();
    }

    @Test
    @DisplayName("Should handle empty list of parcels")
    void testShouldHandleEmptyParcelList() {
        accessCode.setParcels(Collections.emptyList());
        List<Parcel> parcels = accessCode.getParcels();
        assertThat(parcels).isEmpty();
    }

    @Test
    @DisplayName("Should set and retrieve isLocked correctly")
    void testShouldSetAndGetIsLocked() {
        accessCode.setLocked(true);
        assertThat(accessCode.isLocked()).isTrue();
    }

    @Test
    @DisplayName("Should return null when parcels not initialized")
    void testShouldReturnNullParcelsIfNotSet() {
        AccessCode newCode = new AccessCode("AC111", "Test", profile, status, LocalDateTime.now(), false);
        assertThat(newCode.getParcels()).isNull();
    }

    @Test
    @DisplayName("Should return null accessCodeId before persistence")
    void testShouldReturnNullAccessCodeId() {
        assertThat(accessCode.getAccessCodeId()).isNull();
    }

    @Test
    @DisplayName("Should allow manual setting of updateOn")
    void testShouldAllowManualUpdateOn() {
        LocalDateTime newTime = LocalDateTime.of(2024, 1, 1, 12, 0);
        accessCode.setUpdateOn(newTime);
        assertThat(accessCode.getUpdateOn()).isEqualTo(newTime);
    }

    @Test
    @DisplayName("Should allow setting a new profile")
    void testShouldSetNewProfile() {
        Profile newProfile = new Profile();
        accessCode.setProfile(newProfile);
        assertThat(accessCode.getProfile()).isEqualTo(newProfile);
    }

}
