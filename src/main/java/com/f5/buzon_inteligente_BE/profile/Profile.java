package com.f5.buzon_inteligente_BE.profile;

import jakarta.persistence.*;
import com.f5.buzon_inteligente_BE.user.User;
import java.io.Serializable;
import org.apache.commons.lang3.RandomStringUtils;

@Entity
@Table(name = "profiles")
public class Profile implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permanent_credential", nullable = false, unique = true)
    private String permanentCredential;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Profile() {

        this.permanentCredential = RandomStringUtils.randomAlphanumeric(8);
    }

    public Profile(User user) {
        this();
        this.user = user;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
