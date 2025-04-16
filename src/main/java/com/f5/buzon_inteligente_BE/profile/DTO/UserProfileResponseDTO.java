package com.f5.buzon_inteligente_BE.profile.DTO;

import com.f5.buzon_inteligente_BE.profile.Profile;
import com.f5.buzon_inteligente_BE.user.User;

public class UserProfileResponseDTO {

    private String userDni;
    private String userName;
    private String userSurname;
    private String userEmail;

    private String permanentCredential;

    public UserProfileResponseDTO() {
    }

    public UserProfileResponseDTO(String userDni, String userName, String userSurname, String userEmail,
            String permanentCredential) {
        this.userDni = userDni;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.permanentCredential = permanentCredential;
    }

    public static UserProfileResponseDTO fromEntities(User user, Profile profile) {
        return new UserProfileResponseDTO(
                user.getUserDni(),
                user.getUserName(),
                user.getUserSurname(),
                user.getUserEmail(),
                profile.getPermanentCredential()

        );
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

    public String getPermanentCredential() {
        return permanentCredential;
    }

}