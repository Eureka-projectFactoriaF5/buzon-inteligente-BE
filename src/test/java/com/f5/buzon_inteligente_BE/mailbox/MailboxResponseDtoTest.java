package com.f5.buzon_inteligente_BE.mailbox;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MailboxResponseDtoTest {
    @Test
    void testMailboxResponseDto() {
        Long mailboxId = 1L;
        Long mailboxSizeId = 2L;
        Long lockerId = 3L;
        Long mailboxStatusId = 4L;
        int mailboxNumber = 101;

        MailboxResponseDto dto = new MailboxResponseDto(
            mailboxId,
            mailboxSizeId,
            lockerId,
            mailboxStatusId,
            mailboxNumber
        );

        assertEquals(mailboxId, dto.mailboxId());
        assertEquals(mailboxSizeId, dto.getMailboxSizeId());
        assertEquals(lockerId, dto.lockerId());
        assertEquals(mailboxStatusId, dto.getMailboxStatusId());
        assertEquals(mailboxNumber, dto.mailboxNumber());
    }
}
