package com.f5.buzon_inteligente_BE.mailbox;

import jakarta.persistence.*;

@Entity
@Table(name = "mailbox_status")
public class MailboxStatus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mailbox_status_id", nullable = false)
    private Long mailboxStatusId;

    @Column(name = "mailbox_status_name", nullable = false, length = 50)
    private String mailboxStatusName;

    public MailboxStatus() {
    }

    public Long getMailboxStatusId() {
        return mailboxStatusId;
    }

    public String getMailboxStatusName() {
        return mailboxStatusName;
    }

    public MailboxStatus(Long mailboxStatusId, String mailboxStatusName) {
        this.mailboxStatusId = mailboxStatusId;
        this.mailboxStatusName = mailboxStatusName;
    }

    public void setMailboxStatusName(String mailboxStatusName) {
        this.mailboxStatusName = mailboxStatusName;
    }
}
