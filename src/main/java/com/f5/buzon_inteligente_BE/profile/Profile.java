package com.f5.buzon_inteligente_BE.profile;

import jakarta.persistence.*;
import com.f5.buzon_inteligente_BE.user.User;
import java.util.UUID;

@Entity
@Table(name = "profiles")
public class Profile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "permanent_credential", nullable = false, unique = true)
    private String permanentCredential;

    @Column(name = "delivery_person_access_code", nullable = false, unique = true)
    private String deliveryPersonAccessCode;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "user_id")
    private User user;

    public Profile() {
        this.deliveryPersonAccessCode = UUID.randomUUID().toString();
    }

    public Profile(String permanentCredential, User user) {
        this();
        this.permanentCredential = permanentCredential;
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

    public String getDeliveryPersonAccessCode() {
        return deliveryPersonAccessCode;
    }

    public void setDeliveryPersonAccessCode(String deliveryPersonAccessCode) {
        this.deliveryPersonAccessCode = deliveryPersonAccessCode;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}