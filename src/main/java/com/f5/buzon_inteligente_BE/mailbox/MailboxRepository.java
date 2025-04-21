package com.f5.buzon_inteligente_BE.mailbox;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MailboxRepository extends JpaRepository<Mailbox, Long> {

    Optional<Mailbox> findFirstByMailboxSize_IdAndMailboxStatus_Id(
        Long mailboxSizeId,
        Long mailboxStatusId
    );

    Optional<Mailbox> findFirstByMailboxSize_CapacityGreaterThanAndMailboxStatus_IdOrderByMailboxSize_CapacityAsc(
        Integer capacity,
        Long mailboxStatusId
    );
}