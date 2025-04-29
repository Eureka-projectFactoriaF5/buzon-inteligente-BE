package com.f5.buzon_inteligente_BE.accesscode.DTO;

import com.f5.buzon_inteligente_BE.accesscode.AccessCode;
import com.f5.buzon_inteligente_BE.accesscode.AccessCodeStatus;
import com.f5.buzon_inteligente_BE.mailbox.Mailbox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class AccessCodeResponseDTOTest {

    private AccessCode accessCode;
    private AccessCodeStatus status;
    private LocalDateTime now;

    @BeforeEach
    void setUp() {
        accessCode = mock(AccessCode.class);
        status = mock(AccessCodeStatus.class);
        now = LocalDateTime.now();

        when(accessCode.getAccessCodeId()).thenReturn(1L);
        when(accessCode.getAccessCode()).thenReturn("CODE1234");
        when(accessCode.getAccessCodeName()).thenReturn("Test Code");
        when(accessCode.getAccessCodeStatus()).thenReturn(status);
        when(status.getAccessCodeStatusName()).thenReturn("Active");
        when(accessCode.getUpdateOn()).thenReturn(now);
        when(accessCode.isLocked()).thenReturn(false);
    }

    @Test
    @DisplayName("fromEntities should map correctly when mailbox is present")
    void testFromEntitiesWithMailbox() {
        Mailbox mailbox = mock(Mailbox.class);
        when(mailbox.getMailboxNumber()).thenReturn(42);

        AccessCodeResponseDTO dto = AccessCodeResponseDTO.fromEntities(accessCode, mailbox);

        assertAll(
            () -> assertNotNull(dto),
            () -> assertEquals(1L, dto.getAccessCodeId()),
            () -> assertEquals("CODE1234", dto.getAccessCode()),
            () -> assertEquals("Test Code", dto.getAccessCodeName()),
            () -> assertEquals("Active", dto.getAccessCodeStatusName()),
            () -> assertEquals(now, dto.getUpdateOn()),
            () -> assertFalse(dto.isLocked()),
            () -> assertEquals(42, dto.getMailboxNumber())
        );
    }

    @Test
    @DisplayName("fromEntities should set mailboxNumber to -1 when mailbox is null")
    void testFromEntitiesWithNullMailbox() {
        AccessCodeResponseDTO dto = AccessCodeResponseDTO.fromEntities(accessCode, null);

        assertAll(
            () -> assertNotNull(dto),
            () -> assertEquals(1L, dto.getAccessCodeId()),
            () -> assertEquals("CODE1234", dto.getAccessCode()),
            () -> assertEquals("Test Code", dto.getAccessCodeName()),
            () -> assertEquals("Active", dto.getAccessCodeStatusName()),
            () -> assertEquals(now, dto.getUpdateOn()),
            () -> assertFalse(dto.isLocked()),
            () -> assertEquals(-1, dto.getMailboxNumber())
        );
    }
}


