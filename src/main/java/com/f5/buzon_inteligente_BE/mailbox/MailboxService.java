package com.f5.buzon_inteligente_BE.mailbox;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.f5.buzon_inteligente_BE.mailbox.exceptions.MailboxNotFoundException;

@Service
public class MailboxService {

    private final MailboxRepository mailboxRepo;
    private final MailboxSizeRepository sizeRepo;
    private final MailboxStatusRepository statusRepo;

    public MailboxService(MailboxRepository mailboxRepo, MailboxSizeRepository sizeRepo,
            MailboxStatusRepository statusRepo) {
        this.mailboxRepo = mailboxRepo;
        this.sizeRepo = sizeRepo;
        this.statusRepo = statusRepo;
    }

    @Transactional(readOnly = true)
    public Mailbox findNextAvailableMailbox(Long requestedSizeId) {
        MailboxSize requestedSize = sizeRepo.findById(requestedSizeId)
            .orElseThrow(() -> new MailboxNotFoundException("Tamaño no existe"));

        Integer requestedCapacity = requestedSize.getCapacity();

        Long freeStatusId = statusRepo.findByStatusName("FREE")
            .orElseThrow(() -> new MailboxNotFoundException("Estado FREE no definido"))
            .getMailboxStatusId();

        return mailboxRepo.findFirstAvailableByExactSize(requestedSizeId, freeStatusId)
            .or(() ->
                mailboxRepo
                  .findFirstAvailableByMinCapacity(
                      requestedCapacity, freeStatusId
                  )
            )
            .orElseThrow(() ->
                new MailboxNotFoundException("No hay buzones libres de tamaño " +
                                              requestedCapacity + " ni superiores")
            );
    }
}