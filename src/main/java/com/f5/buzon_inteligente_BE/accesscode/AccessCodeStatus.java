package com.f5.buzon_inteligente_BE.accesscode;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

@Entity
@Table(name = "access_code_status")
public class AccessCodeStatus implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "access_code_status_id", nullable = false)
    private Long accessCodeStatusId;

    @Column(name = "access_code_status_name", nullable = false, length = 50)
    private String accessCodeStatusName;

    @OneToMany(mappedBy = "accessCodeStatus", cascade = { CascadeType.PERSIST,
            CascadeType.MERGE })
    @JsonIgnore
    private List<AccessCode> accessCodes = new ArrayList<>();

    public AccessCodeStatus() {
    }

    public AccessCodeStatus(String accessCodeStatusName) {
        this.accessCodeStatusName = accessCodeStatusName;
    }

    public Long getAccessCodeStatusId() {
        return accessCodeStatusId;
    }

    public String getAccessCodeStatusName() {
        return accessCodeStatusName;
    }

    public void setAccessCodeStatusName(String accessCodeStatusName) {
        this.accessCodeStatusName = accessCodeStatusName;
    }

    public List<AccessCode> getAccessCodes() {
        return accessCodes;
    }
}