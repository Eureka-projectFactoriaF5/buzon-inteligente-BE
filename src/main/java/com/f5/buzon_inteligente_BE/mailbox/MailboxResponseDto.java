package com.f5.buzon_inteligente_BE.mailbox;

public record MailboxResponseDto(
    Long mailboxId,
    Long getMailboxSizeId,
    Long lockerId,
    Long getMailboxStatusId,
    int mailboxNumber
) {}
