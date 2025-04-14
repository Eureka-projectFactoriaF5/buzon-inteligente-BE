package com.f5.buzon_inteligente_BE.user;

import java.io.Serializable;

import com.f5.buzon_inteligente_BE.roles.Role;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "user_dni", nullable = false, length = 50, unique = true)
    private String userDni;

    @Column(name = "user_name", nullable = false, length = 50)
    private String userName;

    @Column(name = "user_surname", nullable = false, length = 50)
    private String userSurname;

    @Column(name = "user_email", nullable = false, length = 50, unique = true)
    private String userEmail;

    @Column(name = "user_password", nullable = false, length = 255)
    private String userPassword;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role role;

    public User() {
    }
  
    public User(String userDni, String userName, String userSurname, String userEmail, String userPassword, Role role) {
        this.userDni = userDni;
        this.userName = userName;
        this.userSurname = userSurname;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.role = role;
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

    public String getUserSurname() {  
        return userSurname;
    }

    public Role getRole() {
        return role;
    }
}
