package com.f5.buzon_inteligente_BE.auth.register;

public class RegistrationRequest {

    private String userDni;
    private String userName;
    private String userEmail;
    private String userPassword;

    public RegistrationRequest() {
    }

    public RegistrationRequest(String userDni, String userName, String userEmail, String userPassword) {
        this.userDni = userDni;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public String getUserDni() {
        return userDni;
    }

    public void setUserDni(String userDni) {
        this.userDni = userDni;
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

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

}
