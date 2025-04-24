package com.f5.buzon_inteligente_BE.accesscode.DTO;

public class AccessCodeUpdateStatusRequestDTO {

    private Long accessCodeId;
    private Long accessCodeStatusId;

    public Long getAccessCodeId() {
        return accessCodeId;
    }

    public void setAccessCodeId(Long accessCodeId) {
        this.accessCodeId = accessCodeId;
    }

    public Long getAccessCodeStatus(Long accessCodeStatusId) {    
        return accessCodeStatusId;
    }
    public Long getAccessCodeStatusId() {
        return accessCodeStatusId;
    }
    public void setAccessCodeStatus(Long accessCodeStatusId) {
        this.accessCodeStatusId = accessCodeStatusId;
    }
}
