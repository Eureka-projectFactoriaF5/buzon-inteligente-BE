package com.f5.buzon_inteligente_BE.userprofile;

public class UserProfile {

    private Long userId;
    private String userName;
    private String userEmail;
    private String userDni;

    // Constructor
    public UserProfile(Long userId, String userName, String userEmail, String userDni) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userDni = userDni;
    }

    // Getters and Setters
    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
    }
}
