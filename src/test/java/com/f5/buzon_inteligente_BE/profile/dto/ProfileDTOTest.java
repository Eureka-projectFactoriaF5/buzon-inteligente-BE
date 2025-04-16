package com.f5.buzon_inteligente_BE.profile.dto;

import com.f5.buzon_inteligente_BE.profile.Profile;
import com.f5.buzon_inteligente_BE.profile.DTO.ProfileDTO;
import com.f5.buzon_inteligente_BE.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ProfileDTOTest {

    private Profile profile;
    private User mockUser;

    @BeforeEach
    void setUp() {
        profile = new Profile();
        profile.setId(1L);
        profile.setPermanentCredential("DEFAULT_CREDENTIAL");

        mockUser = new User() {
            @Override
            public Long getUserId() {
                return 100L;
            }
        };
    }

    @Test
    @DisplayName("Should create empty ProfileDTO and set values with setters")
    void testShouldNoArgsConstructorAndSetters() {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(1L);
        dto.setPermanentCredential("ABC123");
        dto.setUserId(10L);

        assertEquals(1L, dto.getId());
        assertEquals("ABC123", dto.getPermanentCredential());
        assertEquals(10L, dto.getUserId());
    }

    @Test
    @DisplayName("Should create ProfileDTO using all-args constructor")
    void testShouldAllArgsConstructor() {
        ProfileDTO dto = new ProfileDTO(2L, "XYZ456", 20L);

        assertEquals(2L, dto.getId());
        assertEquals("XYZ456", dto.getPermanentCredential());
        assertEquals(20L, dto.getUserId());
    }
    
}
