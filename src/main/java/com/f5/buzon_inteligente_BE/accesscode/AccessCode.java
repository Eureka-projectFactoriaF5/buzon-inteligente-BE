package com.f5.buzon_inteligente_BE.accesscode;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.io.Serializable;
import com.f5.buzon_inteligente_BE.profile.Profile;
import com.f5.buzon_inteligente_BE.accesscode.AccessCodeStatus;
import com.f5.buzon_inteligente_BE.parcel.Parcel;

@Entity
@Table(name = "access_code")
public class AccessCode implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_code_id", nullable = false)
    private Long accessCodeId;
    @Column(name = "access_code", nullable = false, unique = true)
    private String accessCode;
    @Column(name = "access_code_name", nullable = false)
    private String accessCodeName;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private Profile profile;

    @ManyToOne
    @JoinColumn(name = "access_code_status_id", nullable = false)
    private AccessCodeStatus accessCodeStatus;

    @OneToMany(mappedBy = "accessCode")
    private List<Parcel> parcels = new ArrayList<>();

    public AccessCode() {
    }

    public AccessCode(String code, String name, Profile profile, AccessCodeStatus accessCodeStatus) {
        this.accessCode = code;
        this.accessCodeName = name;
        this.profile = profile;
        this.accessCodeStatus = accessCodeStatus;
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

    public void setParcels(List<Parcel> parcels) {
        this.parcels = parcels;
    }
}