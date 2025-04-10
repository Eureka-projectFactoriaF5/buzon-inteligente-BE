package com.f5.buzon_inteligente_BE.dto;

public class CredentialRequest {

    private String newCredential;
    private String currentPassword;

    public CredentialRequest() {
    }

    public CredentialRequest(String newCredential, String currentPassword) {
        this.newCredential = newCredential;
        this.currentPassword = currentPassword;
    }

    public String getNewCredential() {
        return newCredential;
    }

    public void setNewCredential(String newCredential) {
        this.newCredential = newCredential;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }
}
