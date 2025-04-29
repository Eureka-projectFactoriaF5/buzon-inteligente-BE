package com.f5.buzon_inteligente_BE.accesscode.DTO;

public class AccessCodeUpdateStatusRequestDTO {

    private Long accessCodeStatusId;
    private Long mailboxId;

  
    public Long getAccessCodeStatusId() {
        return accessCodeStatusId;
    }


    public void setAccessCodeStatusId(Long accessCodeStatusId) {
        this.accessCodeStatusId = accessCodeStatusId;
    }

    public Long getMailboxId() {
        return mailboxId;
    }

    public void setMailboxId(Long mailboxId) {
        this.mailboxId = mailboxId;
    }
}
