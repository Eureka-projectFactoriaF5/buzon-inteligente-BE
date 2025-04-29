package com.f5.buzon_inteligente_BE.accesscode.DTO;

import com.f5.buzon_inteligente_BE.accesscode.AccessCode;
import com.f5.buzon_inteligente_BE.accesscode.AccessCodeStatus;
import com.f5.buzon_inteligente_BE.profile.Profile;
import com.f5.buzon_inteligente_BE.user.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AccessCodeUpdateStatusResponseDTOTest {

    private AccessCode accessCode;
    private AccessCodeStatus accessCodeStatus;
    private Profile profile;
    private User user;
    private LocalDateTime updateTime;

    @BeforeEach
    void setUp() {
        accessCode = mock(AccessCode.class);
        accessCodeStatus = mock(AccessCodeStatus.class);
        profile = mock(Profile.class);
        user = mock(User.class);
        updateTime = LocalDateTime.now();

        when(accessCode.getAccessCodeId()).thenReturn(1L);
        when(accessCode.getUpdateOn()).thenReturn(updateTime);
        when(accessCode.getAccessCodeStatus()).thenReturn(accessCodeStatus);
        when(accessCode.getProfile()).thenReturn(profile);
        when(profile.getUser()).thenReturn(user);
        when(user.getUserName()).thenReturn("TestName");
        when(user.getUserSurname()).thenReturn("TestSurname");
        when(user.getUserDni()).thenReturn("12345678A");
    }

    @Test
    @DisplayName("should correctly map fields from AccessCode to AccessCodeUpdateStatusResponseDTO")
    void testFromEntities() {
        AccessCodeUpdateStatusResponseDTO dto = AccessCodeUpdateStatusResponseDTO.fromEntities(accessCode);

        assertAll(
            () -> assertNotNull(dto),
            () -> assertEquals(1L, dto.getAccessCodeId()),
            () -> assertEquals(updateTime, dto.getUpdateOn()),
            () -> assertEquals(accessCodeStatus, dto.getAccessCodeStatus()),
            () -> assertEquals("TestName", dto.getUserName()),
            () -> assertEquals("TestSurname", dto.getUserSurname()),
            () -> assertEquals("12345678A", dto.getUserDni())
        );
    }
}

