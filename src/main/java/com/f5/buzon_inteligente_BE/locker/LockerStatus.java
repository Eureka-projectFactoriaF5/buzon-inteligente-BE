package com.f5.buzon_inteligente_BE.locker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "locker_status")
public class LockerStatus implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locker_status_id", nullable = false)
    private Long lockerStatusId;

    @Column(name = "locker_status_name", nullable = false, length = 50)
    private String lockerStatusName;
    
    @OneToMany(mappedBy = "lockerStatus")
    private List<Locker> lockers = new ArrayList<>();

    public LockerStatus() {
    }
    public LockerStatus(String lockerStatusName) {
        this.lockerStatusName = lockerStatusName;
    }
    
    public Long getLockerStatusId() {
        return lockerStatusId;
    }
    public String getLockerStatusName() {
        return lockerStatusName;
    }

    public void setLockerStatusName(String lockerStatusName) {
        this.lockerStatusName = lockerStatusName;
    }

    public void setLockers(List<Locker> lockers) {
        this.lockers = lockers;
    }

}
