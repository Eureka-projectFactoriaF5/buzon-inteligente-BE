package com.f5.buzon_inteligente_BE.profile.DTO;

public class ProfileUpdateRequestDTO {

    private String userDni;
    private String userName;
    private String userSurname;
    private String userEmail;
    private String userPassword;

    public ProfileUpdateRequestDTO(String userDni, String userName, String userSurname, String userEmail,
            String userPassword) {
        this.userDni = userDni;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
    }

    public ProfileUpdateRequestDTO() {
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

    public String getUserSurname() {
        return userSurname;
    }

    public void setUserSurname(String userSurname) {
        this.userSurname = userSurname;
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
