package com.f5.buzon_inteligente_BE.dto;

import com.f5.buzon_inteligente_BE.profile.Profile;

public class ProfileDTO {
    
    private Long id;
    private String permanentCredential;
    private String deliveryPersonAccessCode;
    private Long userId;

    public ProfileDTO() {
    }

    public ProfileDTO(Long id, String permanentCredential, String deliveryPersonAccessCode, Long userId) {
        this.id = id;
        this.permanentCredential = permanentCredential;
        this.deliveryPersonAccessCode = deliveryPersonAccessCode;
        this.userId = userId;
    }

    public static ProfileDTO fromEntity(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                profile.getPermanentCredential(),
                profile.getDeliveryPersonAccessCode(),
                profile.getUser() != null ? profile.getUser().getUserId() : null
        );
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPermanentCredential() {
        return permanentCredential;
    }

    public void setPermanentCredential(String permanentCredential) {
        this.permanentCredential = permanentCredential;
    }

    public String getDeliveryPersonAccessCode() {
        return deliveryPersonAccessCode;
    }

    public void setDeliveryPersonAccessCode(String deliveryPersonAccessCode) {
        this.deliveryPersonAccessCode = deliveryPersonAccessCode;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}