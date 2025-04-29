package com.f5.buzon_inteligente_BE.mailbox;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MailboxRepository extends JpaRepository<Mailbox, Long> {

    @Query("""
      SELECT m 
      FROM Mailbox m 
      WHERE m.mailboxSize.id = :mailboxSizeId
        AND m.mailboxStatus.id = :mailboxStatusId
      ORDER BY m.mailboxId ASC
    """)
    List<Mailbox> findAvailableByExactSize(
        @Param("mailboxSizeId") Long mailboxSizeId,
        @Param("mailboxStatusId") Long mailboxStatusId,
        Pageable pageable
    );

    @Query("""
      SELECT m 
      FROM Mailbox m 
      JOIN m.mailboxSize s 
      WHERE s.capacity > :capacity
        AND m.mailboxStatus.id = :mailboxStatusId 
      ORDER BY s.capacity ASC, m.mailboxId ASC
    """)
    List<Mailbox> findAvailableByMinCapacity(
        @Param("capacity") Integer capacity,
        @Param("mailboxStatusId") Long mailboxStatusId,
        Pageable pageable
    );

    Optional<Mailbox>findById(Long mailboxId);
}