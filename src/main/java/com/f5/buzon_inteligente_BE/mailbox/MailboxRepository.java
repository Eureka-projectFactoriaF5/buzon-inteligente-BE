package com.f5.buzon_inteligente_BE.mailbox;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MailboxRepository extends JpaRepository<Mailbox, Long> {

    @Query("""
        SELECT m 
        FROM Mailbox m 
        WHERE m.mailboxSize.id = :mailboxSizeId 
        AND m.mailboxStatus.id = :mailboxStatusId
    """)
    Optional<Mailbox> findFirstAvailableByExactSize(
        @Param("sizeId") Long sizeId, 
        @Param("statusId") Long statusId
    );

    @Query("""
        SELECT m 
        FROM Mailbox m 
        JOIN m.mailboxSize s 
        WHERE s.capacity > :capacity 
        AND m.mailboxStatus.id = :mailboxStatusId 
        ORDER BY s.capacity ASC
    """)
    Optional<Mailbox> findFirstAvailableByMinCapacity(
        @Param("capacity") Integer capacity, 
        @Param("statusId") Long mailboxStatusId
    );
}