package com.f5.buzon_inteligente_BE.mailbox;

import jakarta.persistence.*;
import java.io.Serializable;

import com.f5.buzon_inteligente_BE.locker.Locker;

@Entity
@Table(name = "mailboxes")
public class Mailbox implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mailbox_id", nullable = false)
    private Long mailboxId;

    @ManyToOne
    @JoinColumn(name = "mailbox_size_id", nullable = false)
    private MailboxSize mailboxSize;

    @ManyToOne
    @JoinColumn(name = "locker_id", nullable = false)
    private Locker locker;

    @ManyToOne
    @JoinColumn(name = "mailbox_status_id", nullable = false)
    private MailboxStatus mailboxStatus;

    @Column(name = "mailbox_number", nullable = false)
    private int mailboxNumber;

    public Mailbox() {}

    public Long getMailboxId() {
        return mailboxId;
    }

    public MailboxSize getMailboxSize() {
        return mailboxSize;
    }

    public void setMailboxSize(MailboxSize mailboxSize) {
        this.mailboxSize = mailboxSize;
    }

    public Locker getLocker() {
        return locker;
    }

    public MailboxStatus getMailboxStatus() {
        return mailboxStatus;
    }

    public void setMailboxStatus(MailboxStatus mailboxStatus) {
        this.mailboxStatus = mailboxStatus;
    }

    public int getMailboxNumber() {
        return mailboxNumber;
    }

    public void setMailboxNumber(int mailboxNumber) {
        this.mailboxNumber = mailboxNumber;
    }
}