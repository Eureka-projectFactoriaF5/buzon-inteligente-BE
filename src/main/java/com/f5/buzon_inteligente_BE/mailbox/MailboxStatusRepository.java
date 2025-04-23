package com.f5.buzon_inteligente_BE.mailbox;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MailboxStatusRepository extends JpaRepository<MailboxStatus, Long> {
    Optional<MailboxStatus> findByMailboxStatusName(String mailboxStatusName);
}