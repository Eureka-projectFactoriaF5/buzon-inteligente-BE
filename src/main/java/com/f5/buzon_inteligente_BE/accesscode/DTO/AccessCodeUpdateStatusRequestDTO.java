package com.f5.buzon_inteligente_BE.accesscode.DTO;

public class AccessCodeUpdateStatusRequestDTO {
    private Long accessCodeStatusId;
    private Long mailboxId;

    public Long getAccessCodeStatus(Long accessCodeStatusId) {    
        return accessCodeStatusId;
    }
    public Long getAccessCodeStatusId() {
        return accessCodeStatusId;
    }
    public void setAccessCodeStatus(Long accessCodeStatusId) {
        this.accessCodeStatusId = accessCodeStatusId;
    }

    public Long getMailboxId() {
        return mailboxId;
    }
}
