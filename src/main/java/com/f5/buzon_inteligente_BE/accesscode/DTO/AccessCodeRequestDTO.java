package com.f5.buzon_inteligente_BE.accesscode.DTO;

public class AccessCodeRequestDTO {
    private Long profileId;
    private String accessCodeName;

    public AccessCodeRequestDTO() {
    }

    public AccessCodeRequestDTO(Long profileId, String accessCodeName) {
        this.profileId = profileId;
        this.accessCodeName = accessCodeName;
    }

    public Long getProfileId() {
        return profileId;
    }

    public void setProfileId(Long profileId) {
        this.profileId = profileId;
    }

    public String getAccessCodeName() {
        return accessCodeName;
    }

    public void setAccessCodeName(String accessCodeName) {
        this.accessCodeName = accessCodeName;
    }

}
