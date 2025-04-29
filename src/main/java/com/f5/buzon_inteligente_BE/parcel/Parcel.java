package com.f5.buzon_inteligente_BE.parcel;

import java.io.Serializable;
import java.time.LocalDateTime;
import com.f5.buzon_inteligente_BE.accesscode.AccessCode;
import com.f5.buzon_inteligente_BE.locker.Locker;
import com.f5.buzon_inteligente_BE.mailbox.Mailbox;
import jakarta.persistence.*;

@Entity
@Table(name="parcels")
public class Parcel implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long parcelId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "access_code_id", referencedColumnName = "access_code_id",  nullable = false)
    private AccessCode accessCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mailbox_id", referencedColumnName = "mailbox_id",  nullable = false)
    private Mailbox mailbox;

    @Column(name="delivery_date",  nullable = false)
    private LocalDateTime deliveryDate;

    @Column(name="alarm_date",  nullable = false)
    private LocalDateTime alarmDate;

    @Column(name="deadline_date",  nullable = false)
    private LocalDateTime deadlineDate;

    public Parcel(){}

    public Parcel(AccessCode accessCode, Mailbox mailbox, LocalDateTime deliveryDate, LocalDateTime alarmDate, LocalDateTime deadlineDate){
        this.accessCode = accessCode;
        this.mailbox = mailbox;
        this.deliveryDate = LocalDateTime.now();
        this.alarmDate = alarmDate;
        this.deadlineDate = deadlineDate;
    }

    public Long getParcelId(){
        return parcelId;
    }
    
    public AccessCode getAccessCode(){
        return accessCode;
    }

    public Mailbox getMailbox(){
        return mailbox;
    }

    public LocalDateTime getDeliveryDate(){
        return deliveryDate;
    }

    public LocalDateTime getAlarmDate(){
        return alarmDate;
    }

    public LocalDateTime getDeadlineDate(){
        return deadlineDate;
    }

    public void setAlarmDate(Long alarmHours){
        this.alarmDate = deadlineDate.minusHours(alarmHours);
    }

    public void setDeadlineDate(Locker locker){
        Long limitHours = locker.getTimeLimit();
        this.deadlineDate = deliveryDate.plusHours(limitHours);
    }
}
