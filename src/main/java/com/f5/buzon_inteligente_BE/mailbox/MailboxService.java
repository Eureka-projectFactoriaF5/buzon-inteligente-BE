package com.f5.buzon_inteligente_BE.mailbox;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.f5.buzon_inteligente_BE.mailbox.exceptions.MailboxNotFoundException;

@Service
public class MailboxService {

    private final MailboxRepository mailboxRepo;
    private final MailboxSizeRepository sizeRepo;
    private final MailboxStatusRepository statusRepo;

    private static final String FREE_STATUS = "FREE";

    public MailboxService(MailboxRepository mailboxRepo, MailboxSizeRepository sizeRepo,
            MailboxStatusRepository statusRepo) {
        this.mailboxRepo = mailboxRepo;
        this.sizeRepo = sizeRepo;
        this.statusRepo = statusRepo;
    }

    @Transactional(readOnly = true)
    public Mailbox findNextAvailableMailbox(Long requestedSizeId) {

        MailboxSize requestedSize = sizeRepo.findById(requestedSizeId)
                .orElseThrow(() -> new MailboxNotFoundException(
                        "Tamaño de buzón no encontrado: " + requestedSizeId));

        Long freeStatusId = statusRepo.findByStatusName(FREE_STATUS)
                .orElseThrow(() -> new MailboxNotFoundException("Estado FREE no definido"))
                .getMailboxStatusId();

        Optional<Mailbox> exact = mailboxRepo
                .findFirstByMailboxSize_IdAndMailboxStatus_Id(
                        requestedSize.getMailboxSizeId(), freeStatusId);

        if (exact.isPresent()) {
            return exact.get();
        }

        return mailboxRepo
                .findFirstByMailboxSize_IdGreaterThanAndMailboxStatus_IdOrderByMailboxSize_IdAsc(
                        requestedSize.getMailboxSizeId(), freeStatusId)
                .orElseThrow(() -> new MailboxNotFoundException(
                        "No hay buzones libres de tamaño " + requestedSizeId + " ni superiores"));
    }
}