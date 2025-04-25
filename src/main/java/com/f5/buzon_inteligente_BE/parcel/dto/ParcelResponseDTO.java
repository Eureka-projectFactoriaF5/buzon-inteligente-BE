package com.f5.buzon_inteligente_BE.parcel.dto;

import java.time.LocalDateTime;
import com.f5.buzon_inteligente_BE.parcel.Parcel;

public class ParcelResponseDTO {

    private Long parcelId;
    private String accessCode;
    private Long mailboxId;
    private int mailboxNumber;
    private LocalDateTime deliveryDate;
    private LocalDateTime deadlineDate;

    public ParcelResponseDTO(Long parcelId, String accessCode, Long mailboxId, int mailboxNumber,
                             LocalDateTime deliveryDate, LocalDateTime deadlineDate) {
        this.parcelId = parcelId;
        this.accessCode = accessCode;
        this.mailboxId = mailboxId;
        this.mailboxNumber = mailboxNumber;
        this.deliveryDate = deliveryDate;
        this.deadlineDate = deadlineDate;
    }

    public static ParcelResponseDTO fromEntity(Parcel parcel) {
        return new ParcelResponseDTO(
                parcel.getParcelId(),
                parcel.getAccessCode().getAccessCode(),
                parcel.getMailbox().getMailboxId(),
                parcel.getMailbox().getMailboxNumber(),
                parcel.getDeliveryDate(),
                parcel.getDeadlineDate());
    }

    public Long getParcelId() {
        return parcelId;
    }

    public String getAccessCode() {
        return accessCode;
    }

    public Long getMailboxId() {
        return mailboxId;
    }

    public int getMailboxNumber() {
        return mailboxNumber;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public LocalDateTime getDeadlineDate() {
        return deadlineDate;
    }
}

