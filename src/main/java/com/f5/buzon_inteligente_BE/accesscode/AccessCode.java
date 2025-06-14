package com.f5.buzon_inteligente_BE.accesscode;

import jakarta.persistence.*;

import java.util.List;
import java.io.Serializable;
import java.time.LocalDateTime;

import com.f5.buzon_inteligente_BE.profile.Profile;
import com.f5.buzon_inteligente_BE.parcel.Parcel;
@Entity
@Table(name = "access_codes")
public class AccessCode implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_code_id", nullable = false)
    private Long accessCodeId;
    @Column(name = "access_code", nullable = false, unique = true)
    private String accessCode;
    @Column(name = "access_code_name", nullable = false)
    private String accessCodeName;

    @Column(name = "update_on", nullable = false)
    private LocalDateTime updateOn;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "access_code_status_id", nullable = false)
    private AccessCodeStatus accessCodeStatus;

    @OneToMany(mappedBy = "accessCode", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Parcel> parcels;

    @Column(name ="is_locked", nullable = false)
    private boolean isLocked = false;

    public AccessCode() {
    }

    public AccessCode(String code, String name, Profile profile, AccessCodeStatus accessCodeStatus, LocalDateTime updateOn,boolean isLocked) {
        this.accessCode = code;
        this.accessCodeName = name;
        this.profile = profile;
        this.accessCodeStatus = accessCodeStatus;
        this.updateOn = LocalDateTime.now();
        this.isLocked = isLocked;
    }

    public Long getAccessCodeId() {
        return accessCodeId;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public String getAccessCodeName() {
        return accessCodeName;
    }

    public List<Parcel> getParcels() {
        return parcels;
    }

    public void setAccessCodeName(String accessCodeName) {
        this.accessCodeName = accessCodeName;
    }

    public AccessCodeStatus getAccessCodeStatus() {
        return accessCodeStatus;
    }

    public void setAccessCodeStatus(AccessCodeStatus accessCodeStatus) {
        this.accessCodeStatus = accessCodeStatus;
        this.updateOn = LocalDateTime.now();
    }

    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
    }

    public Profile getProfile() {
        return profile;
    }
    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public LocalDateTime getUpdateOn() {
        return updateOn;
    }

    public void setUpdateOn(LocalDateTime updateOn) {
        this.updateOn = updateOn;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

}