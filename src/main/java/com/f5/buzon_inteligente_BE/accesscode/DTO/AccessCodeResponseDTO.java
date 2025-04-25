package com.f5.buzon_inteligente_BE.accesscode.DTO;

import java.time.LocalDateTime;
import com.f5.buzon_inteligente_BE.accesscode.AccessCode;
import com.f5.buzon_inteligente_BE.accesscode.AccessCodeStatus;
import com.f5.buzon_inteligente_BE.mailbox.Mailbox;

public class AccessCodeResponseDTO {
    private Long accessCodeId;
    private String accessCode;
    private String accessCodeName;
    private AccessCodeStatus accessCodeStatus;
    private LocalDateTime updateOn;
    private boolean isLocked = false;
    private int mailboxNumber;

    public AccessCodeResponseDTO() {
    }

    public AccessCodeResponseDTO(Long accessCodeId, String code, String name, AccessCodeStatus accessCodeStatus,
            LocalDateTime updateOn, boolean isLocked, int mailboxNumber) {
        this.accessCodeId = accessCodeId;
        this.accessCode = code;
        this.accessCodeName = name;
        this.accessCodeStatus = accessCodeStatus;
        this.updateOn = LocalDateTime.now();
        this.isLocked = isLocked;
        this.mailboxNumber = mailboxNumber;
    }

    public AccessCodeResponseDTO(Long accessCodeId, String code, String name, AccessCodeStatus accessCodeStatus,
            LocalDateTime updateOn,
            boolean isLocked) {
        this.accessCode = code;
        this.accessCodeName = name;
        this.accessCodeStatus = accessCodeStatus;
        this.updateOn = LocalDateTime.now();
        this.isLocked = isLocked;
    }

    public static AccessCodeResponseDTO fromEntities(AccessCode accessCode, Mailbox mailbox) {
        return new AccessCodeResponseDTO(
                accessCode.getAccessCodeId(),
                accessCode.getAccessCode(),
                accessCode.getAccessCodeName(),
                accessCode.getAccessCodeStatus(),
                accessCode.getUpdateOn(),
                accessCode.isLocked(),
                mailbox.getMailboxNumber());
    }

    public static AccessCodeResponseDTO fromEntities(AccessCode accessCode) {
        return new AccessCodeResponseDTO(
                accessCode.getAccessCodeId(),
                accessCode.getAccessCode(),
                accessCode.getAccessCodeName(),
                accessCode.getAccessCodeStatus(),
                accessCode.getUpdateOn(),
                accessCode.isLocked());
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

    public AccessCodeStatus getAccessCodeStatus() {
        return accessCodeStatus;
    }

    public void setAccessCodeStatus(AccessCodeStatus accessCodeStatus) {
        this.accessCodeStatus = accessCodeStatus;
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

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    public int getMailboxNumber() {
        return mailboxNumber;
    }

    public void setMailboxNumber(int mailboxNumber) {
        this.mailboxNumber = mailboxNumber;
    }

}
