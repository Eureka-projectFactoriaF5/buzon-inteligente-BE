package com.f5.buzon_inteligente_BE.accesscode.DTO;

import java.time.LocalDateTime;

import com.f5.buzon_inteligente_BE.accesscode.AccessCode;
import com.f5.buzon_inteligente_BE.accesscode.AccessCodeStatus;

public class AccessCodeUpdateStatusResponseDTO {

    private Long accessCodeId;
    private LocalDateTime updateOn;
    private AccessCodeStatus accessCodeStatus;
    private String userName;
    private String userSurname;
    private String userDni;

    public AccessCodeUpdateStatusResponseDTO(AccessCode accessCode) {
        this.accessCodeId = accessCode.getAccessCodeId();
        this.updateOn = accessCode.getUpdateOn();
        this.accessCodeStatus = accessCode.getAccessCodeStatus();
        this.userName = accessCode.getProfile().getUser().getUserName();
        this.userSurname = accessCode.getProfile().getUser().getUserSurname();
        this.userDni = accessCode.getProfile().getUser().getUserDni();
    }
    
    public Long getAccessCodeId() {
        return accessCodeId;
    }

    public LocalDateTime getUpdateOn() {
        return updateOn;
    }

    public AccessCodeStatus getAccessCodeStatus() {
        return accessCodeStatus;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserSurname() {
        return userSurname;
    }

    public String getUserDni() {
        return userDni;
    }

    public static AccessCodeUpdateStatusResponseDTO fromEntities(AccessCode updatedAccessCode) {
        return new AccessCodeUpdateStatusResponseDTO(updatedAccessCode);
    }
}
