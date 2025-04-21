package com.f5.buzon_inteligente_BE.mailbox;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "mailbox_sizes")
public class MailboxSize {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mailbox_size_id", nullable = false)
    private Long mailboxSizeId;

    @Column(name = "size_name", nullable = false, length = 50)
    private String mailboxSizeName;

    @Column(name = "capacity", nullable = false)
    private Integer capacity;

    public MailboxSize() {
    }

    public MailboxSize(String mailboxSizeName) {
        this.mailboxSizeName = mailboxSizeName;
    }

    public Long getMailboxSizeId() {
        return mailboxSizeId;
    }

    public String getMailboxSizeName() {
        return mailboxSizeName;
    }

    public void setMailboxSizeName(String mailboxSizeName) {
        this.mailboxSizeName = mailboxSizeName;
    }
    
    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

}
