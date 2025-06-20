package com.f5.buzon_inteligente_BE.locker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.f5.buzon_inteligente_BE.user.User;
import com.f5.buzon_inteligente_BE.mailbox.Mailbox;

import jakarta.persistence.*;

@Entity
@Table(name = "lockers")
public class Locker implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "locker_id", nullable = false)
    private Long lockerId;

    @Column(name = "address", nullable = false, length = 100)
    private String address;

    @Column(name = "time_limit", nullable = false)
    private Long timeLimit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locker_status_id", nullable = false)
    private LockerStatus lockerStatus;

    @OneToMany(mappedBy = "locker", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Mailbox> mailboxes = new ArrayList<>();

    @OneToMany(mappedBy = "locker", cascade = CascadeType.PERSIST)
    
    private List<User> users = new ArrayList<>();

    public Locker() {
    }

    public Locker(String address, Long timeLimit, LockerStatus lockerStatus) {
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

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(Long timeLimit) {
        this.timeLimit = timeLimit;
    }

    public LockerStatus getLockerStatus() {
        return lockerStatus;
    }

    public void setLockerStatus(LockerStatus lockerStatus) {
        this.lockerStatus = lockerStatus;
    }

    public List<Mailbox> getMailboxes() {
        return mailboxes;
    }

    public List<User> getUsers() {
        return users;
    }
}

