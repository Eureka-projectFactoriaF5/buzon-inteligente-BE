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
    private Long profileId;

    @ManyToOne
    @JoinColumn(name = "access_code_status_id", nullable = false)
    private Long accessCodeStatusId;

    @OneToMany(mappedBy = "accessCode")
    private List<Parcel> parcels = new ArrayList<>();

    public AccessCode() {
    }

    public AccessCode(String code, String name, Long profileId, Long statusId) {
        this.accessCode = code;
        this.accessCodeName = name;
        this.profileId = profileId;
        this.accessCodeStatusId = statusId;
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

    public void setAccessCodeName(String accessCodeName) {
        this.accessCodeName = accessCodeName;
    }

    public Long getAccessCodeStatusId() {
        return accessCodeStatusId;
    }

    public Long getProfileId() {
        return profileId;
    }
}