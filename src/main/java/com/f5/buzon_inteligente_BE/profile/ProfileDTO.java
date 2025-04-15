package com.f5.buzon_inteligente_BE.profile;

public class ProfileDTO {
    
    private Long id;
    private String permanentCredential;
    private Long userId;

    public ProfileDTO() {
    }

    public ProfileDTO(Long id, String permanentCredential, Long userId) {
        this.id = id;
        this.permanentCredential = permanentCredential;
        this.userId = userId;
    }

    public static ProfileDTO fromEntity(Profile profile) {
        return new ProfileDTO(
                profile.getId(),
                profile.getPermanentCredential(),
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
