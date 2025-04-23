package com.f5.buzon_inteligente_BE.mailbox;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mailboxes")
public class MailboxController {

    private final MailboxService mailboxService;

    public MailboxController(MailboxService mailboxService) {
        this.mailboxService = mailboxService;
    }

    @GetMapping("/available")
    public ResponseEntity<MailboxResponseDto> getAvailableMailbox(@RequestParam Long sizeId) {
        Mailbox mailbox = mailboxService.findNextAvailableMailbox(sizeId);

        MailboxResponseDto response = new MailboxResponseDto(
            mailbox.getMailboxId(),
            mailbox.getMailboxSize().getMailboxSizeId(),
            mailbox.getLocker().getLockerId(),
            mailbox.getMailboxStatus().getMailboxStatusId(),
            mailbox.getMailboxNumber()
        );

        return ResponseEntity.ok(response);
    }
}