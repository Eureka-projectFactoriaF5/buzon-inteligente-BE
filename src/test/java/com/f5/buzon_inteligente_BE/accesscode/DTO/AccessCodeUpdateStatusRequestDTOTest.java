package com.f5.buzon_inteligente_BE.accesscode.DTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AccessCodeUpdateStatusRequestDTOTest {

    @Test
    @DisplayName("should set and get accessCodeStatusId and mailboxId correctly (using available methods)")
    void testAccessCodeUpdateStatusRequestDTO() {
        AccessCodeUpdateStatusRequestDTO dto = new AccessCodeUpdateStatusRequestDTO();

        dto.setAccessCodeStatus(7L);
        assertEquals(7L, dto.getAccessCodeStatusId());

        Long result = dto.getAccessCodeStatus(123L);
        assertEquals(123L, result);
        assertNull(dto.getMailboxId());
    }
}
