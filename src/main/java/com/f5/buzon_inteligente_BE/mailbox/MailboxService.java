package com.f5.buzon_inteligente_BE.mailbox;


import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        Pageable firstOne = PageRequest.of(0,1);
        MailboxSize requestedSize = sizeRepo.findById(requestedSizeId)
            .orElseThrow(() -> new MailboxNotFoundException("Requested size does not exist."));

        Integer requestedCapacity = requestedSize.getCapacity();

        Long freeStatusId = statusRepo.findByMailboxStatusName("FREE")
            .orElseThrow(() -> new MailboxNotFoundException("FREE status is not defined."))
            .getMailboxStatusId();

            Optional<Mailbox> exact = mailboxRepo
            .findAvailableByExactSize(requestedSizeId, freeStatusId, firstOne)
            .stream().findFirst();
    
            Optional<Mailbox> next = mailboxRepo
            .findAvailableByMinCapacity(requestedCapacity, freeStatusId, firstOne)
            .stream().findFirst();

            return exact.or(() -> next)
                .orElseThrow(() -> new MailboxNotFoundException(
                    "No free mailboxes of size " + requestedCapacity + " or larger are available."));
    }

    @Transactional
    public void updateMailboxStatus(Long mailboxId, String statusName) {
        Mailbox mailbox = mailboxRepo.findById(mailboxId)
            .orElseThrow(() -> new MailboxNotFoundException("Mailbox not found with id " + mailboxId));

        MailboxStatus newStatus = statusRepo.findByMailboxStatusName(statusName)
            .orElseThrow(() -> new MailboxNotFoundException("MailboxStatus not found: " + statusName));

        mailbox.setMailboxStatus(newStatus);
        mailboxRepo.save(mailbox);
    }
}