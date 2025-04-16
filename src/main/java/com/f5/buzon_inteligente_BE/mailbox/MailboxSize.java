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
    @Column(name = "mailbox_size_id",nullable = false)    
    private Long mailboxSizeId;

@Column(name = "size_name",nullable =  false,length = 50) 
    private String mailboxStatusName;

public MailboxSize() {
}


public MailboxSize(Long mailboxSizeId, String mailboxStatusName) {
    this.mailboxSizeId = mailboxSizeId;
    this.mailboxStatusName = mailboxStatusName;
}


public Long getMailboxSizeId() {
    return mailboxSizeId;
}


public String getMailboxStatusName() {
    return mailboxStatusName;
}

public void setMailboxStatusName(String mailboxStatusName) {
    this.mailboxStatusName = mailboxStatusName;
}


}
