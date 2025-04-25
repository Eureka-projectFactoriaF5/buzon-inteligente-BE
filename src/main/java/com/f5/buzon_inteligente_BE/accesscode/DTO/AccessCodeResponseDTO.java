package com.f5.buzon_inteligente_BE.accesscode.DTO;

import java.time.LocalDateTime;
import com.f5.buzon_inteligente_BE.accesscode.AccessCode;
import com.f5.buzon_inteligente_BE.mailbox.Mailbox;

public class AccessCodeResponseDTO {
    private Long accessCodeId;
    private String accessCode;
    private String accessCodeName;
    private String accessCodeStatusName;
    private LocalDateTime updateOn;
    private boolean isLocked;
    private int mailboxNumber;

    public AccessCodeResponseDTO(
            Long accessCodeId,
            String accessCode,
            String accessCodeName,
            String accessCodeStatusName,
            LocalDateTime updateOn,
            boolean isLocked,
            int mailboxNumber) {
        this.accessCodeId = accessCodeId;
        this.accessCode = accessCode;
        this.accessCodeName = accessCodeName;
        this.accessCodeStatusName = accessCodeStatusName;
        this.updateOn = updateOn;
        this.isLocked = isLocked;
        this.mailboxNumber = mailboxNumber;
    }

    public static AccessCodeResponseDTO fromEntities(AccessCode accessCode, Mailbox mailbox) {
        int mailboxNumber = (mailbox != null) ? mailbox.getMailboxNumber() : -1;
        return new AccessCodeResponseDTO(
                accessCode.getAccessCodeId(),
                accessCode.getAccessCode(),
                accessCode.getAccessCodeName(),
                accessCode.getAccessCodeStatus().getAccessCodeStatusName(),
                accessCode.getUpdateOn(),
                accessCode.isLocked(),
                mailboxNumber);
    }

    public Long getAccessCodeId() {
        return accessCodeId;
    }

    public void setAccessCodeId(Long accessCodeId) {
        this.accessCodeId = accessCodeId;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public void setAccessCode(String accessCode) {
        this.accessCode = accessCode;
    }

    public String getAccessCodeName() {
        return accessCodeName;
    }

    public void setAccessCodeName(String accessCodeName) {
        this.accessCodeName = accessCodeName;
    }

    public String getAccessCodeStatusName() {
        return accessCodeStatusName;
    }

    public void setAccessCodeStatusName(String accessCodeStatusName) {
        this.accessCodeStatusName = accessCodeStatusName;
    }

    public LocalDateTime getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(LocalDateTime updateOn) {
        this.updateOn = updateOn;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean locked) {
        isLocked = locked;
    }

    public int getMailboxNumber() {
        return mailboxNumber;
    }

    public void setMailboxNumber(int mailboxNumber) {
        this.mailboxNumber = mailboxNumber;
    }
}