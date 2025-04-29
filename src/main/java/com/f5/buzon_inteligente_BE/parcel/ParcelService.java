package com.f5.buzon_inteligente_BE.parcel;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.f5.buzon_inteligente_BE.accesscode.AccessCode;
import com.f5.buzon_inteligente_BE.accesscode.AccessCodeRepository;
import com.f5.buzon_inteligente_BE.accesscode.AccessCodeStatus;
import com.f5.buzon_inteligente_BE.accesscode.AccessCodeStatusRepository;
import com.f5.buzon_inteligente_BE.locker.Locker;
import com.f5.buzon_inteligente_BE.mailbox.Mailbox;
import com.f5.buzon_inteligente_BE.parcel.dto.ParcelResponseDTO;
import com.f5.buzon_inteligente_BE.parcel.exceptions.ParcelException;

import org.springframework.transaction.annotation.Transactional;

@Service
public class ParcelService {

    private final ParcelRepository parcelRepository;
    private final AccessCodeRepository accessCodeRepository;
    private final AccessCodeStatusRepository accessCodeStatusRepository;

    public ParcelService(ParcelRepository parcelRepository,
            AccessCodeRepository accessCodeRepository,
            AccessCodeStatusRepository accessCodeStatusRepository) {
        this.parcelRepository = parcelRepository;
        this.accessCodeRepository = accessCodeRepository;
        this.accessCodeStatusRepository = accessCodeStatusRepository;
    }

    @Transactional
    public ParcelResponseDTO createParcelAndUpdateAccessCodeStatus(
            AccessCode accessCode,
            Mailbox mailbox,
            Locker locker) {
        LocalDateTime now = LocalDateTime.now();

        try {
            parcelRepository.findTopByAccessCodeOrderByDeliveryDateDesc(accessCode)
                    .ifPresent(previousParcel -> {
                        if (previousParcel.getDeadlineDate().isBefore(now)) {
                            AccessCodeStatus vencido = accessCodeStatusRepository
                                    .findByAccessCodeStatusName("No recogido")
                                    .orElseThrow(() -> new IllegalStateException("Estado 'No recogido' no encontrado"));

                            accessCode.setAccessCodeStatus(vencido);
                            accessCodeRepository.save(accessCode);

                            throw new IllegalStateException(
                                    "El plazo anterior venció. AccessCode marcado como 'No recogido'.");
                        }
                    });

            Parcel newParcel = new Parcel(accessCode, mailbox, now, now.plusDays(3), null);
            newParcel.setDeadlineDate(locker);
            parcelRepository.save(newParcel);

            return ParcelResponseDTO.fromEntity(newParcel);
        } catch (IllegalStateException e) {

            throw new ParcelException("No se pudo crear el parcel: " + e.getMessage(), e);
        }
    }
}
