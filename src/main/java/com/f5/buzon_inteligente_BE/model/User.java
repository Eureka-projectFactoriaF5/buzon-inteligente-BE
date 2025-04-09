package com.f5.buzon_inteligente_BE.model;

import java.io.Serializable;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_dni", nullable = false, length = 50)
    private String userDni;

    @Column(name="user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "user_email", nullable = false, length = 50)
    private String userEmail;

    @Column(name = "user_password", nullable = false, length = 50)
    private String userPassword;

    @Column(name = "credential_id", nullable = false)
    private Long credentialId;

    @Column(name = "locker_id", nullable = false)
    private Long lockerId;

    public User() { } 

    public User(Long userId, String userDni, String userName, String userEmail, String userPassword, Long credentialId,
            Long lockerId) {
        this.userId = userId;
        this.userDni = userDni;
        this.userName = userName;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.credentialId = credentialId;
        this.lockerId = lockerId;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserDni() {
        return userDni;
    }

    public String getUserName() {
        return userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public Long getLockerId() {
        return lockerId;
    }

}
