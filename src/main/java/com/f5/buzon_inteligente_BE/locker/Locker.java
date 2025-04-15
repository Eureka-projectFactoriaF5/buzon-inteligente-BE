package com.f5.buzon_inteligente_BE.locker;

import java.io.Serializable;
import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "lockers")
public class Locker implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locker_id", nullable = false)
    private Long lockerId;
    
    @Column(name = "address", nullable = false, length = 100)
    private String address;
    
    @Column(name = "time_limit", nullable = false)
    private Integer timeLimit;
    
    @ManyToOne
    @JoinColumn(name = "locker_status_id", nullable = false)
    private LockerStatus lockerStatus;

    public Locker() {
    }
    
    public Locker(String address, Integer timeLimit, LockerStatus lockerStatus) {
        this.address = address;
        this.timeLimit = timeLimit;
        this.lockerStatus = lockerStatus;

    }

    public Long getLockerId() {
        return lockerId;
    }
    
    public String getAddress() {
        return address;
    }
    
    public Integer getTimeLimit() {
        return timeLimit;
    }

    public LockerStatus getLockerStatus() {
        return lockerStatus;
    }
}