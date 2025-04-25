package com.f5.buzon_inteligente_BE.accesscode;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.f5.buzon_inteligente_BE.accesscode.DTO.AccessCodeRequestDTO;
import com.f5.buzon_inteligente_BE.accesscode.DTO.AccessCodeResponseDTO;
import com.f5.buzon_inteligente_BE.accesscode.DTO.AccessCodeUpdateStatusRequestDTO;
import com.f5.buzon_inteligente_BE.locker.Locker;
import com.f5.buzon_inteligente_BE.mailbox.Mailbox;
import com.f5.buzon_inteligente_BE.mailbox.MailboxRepository;
import com.f5.buzon_inteligente_BE.mailbox.MailboxService;
import com.f5.buzon_inteligente_BE.parcel.ParcelService;
import com.f5.buzon_inteligente_BE.profile.Profile;
import com.f5.buzon_inteligente_BE.profile.ProfileRepository;

@Service
public class AccessCodeService {

    private final AccessCodeRepository accessCodeRepository;
    private final AccessCodeStatusRepository statusRepository;
    private final ProfileRepository profileRepository;
    private MailboxService mailboxService;
    private ParcelService parcelService;
    private MailboxRepository mailboxRepository;

    public AccessCodeService(AccessCodeRepository accessCodeRepository,
            AccessCodeStatusRepository statusRepository,
            ProfileRepository profileRepository,
            MailboxService mailboxService,
            MailboxRepository mailboxRepository,
            ParcelService parcelService
     ) {
        this.accessCodeRepository = accessCodeRepository;
        this.statusRepository = statusRepository;
        this.profileRepository = profileRepository;
        this.mailboxService = mailboxService;
        this.mailboxRepository = mailboxRepository;
        this.parcelService = parcelService;
    }

    @Transactional
    public AccessCode createAccessCode(AccessCodeRequestDTO dto) {
        Profile profile = profileRepository.findById(dto.getProfileId())
                .orElseThrow(() -> new AccessCodeException("Perfil no encontrado con ID: " + dto.getProfileId()));

        AccessCodeStatus defaultStatus = statusRepository.findByAccessCodeStatusName("Pendiente")
                .orElseThrow(() -> new AccessCodeException("Estado 'Pendiente' no encontrado"));

        String code;
        do {
            code = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
        } while (accessCodeRepository.existsByAccessCode(code));

        AccessCode newCode = new AccessCode(
                code,
                dto.getAccessCodeName(),
                profile,
                defaultStatus,
                LocalDateTime.now(),
                false);

        return accessCodeRepository.save(newCode);
    }

    @Transactional(readOnly = true)
    public List<AccessCode> getAccessCodesByProfileId(Long profileId) {
        return accessCodeRepository.findAllByProfile_Id(profileId);
    }

    public AccessCode updateAccessCodeStatus(Long accessCodeId, AccessCodeUpdateStatusRequestDTO accessCodeRequestDTO) {
        AccessCode accessCode = accessCodeRepository.findById(accessCodeId)
                .orElseThrow(() -> new AccessCodeException("Código de acceso no encontrado con ID: " + accessCodeId));

        AccessCodeStatus accessCodeStatus = statusRepository.findById(accessCodeRequestDTO.getAccessCodeStatusId())
                .orElseThrow(() -> new AccessCodeException(
                        "Estado no encontrado con ID: " + accessCodeRequestDTO.getAccessCodeStatusId()));
        
        if (accessCodeStatus.getAccessCodeStatusId() == 3 || accessCodeStatus.getAccessCodeStatusId() == 4) {// entregado
            Mailbox mailbox = mailboxRepository.findById(accessCodeRequestDTO.getMailboxId())
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new AccessCodeException(
                            "Buzón no encontrada con ID: " + accessCodeRequestDTO.getMailboxId()));
            Locker locker = mailbox.getLocker();
            parcelService.createParcelAndUpdateAccessCodeStatus(accessCode, mailbox, locker);

            mailboxService.updateMailboxStatus(accessCodeRequestDTO.getMailboxId(), "OCCUPIED");
        }
        if (accessCodeStatus.getAccessCodeStatusId() == 4) {// entregado
            accessCode.setLocked(true);
        }
        if (accessCodeStatus.getAccessCodeStatusId() == 6 || accessCodeStatus.getAccessCodeStatusId() == 7) {
            mailboxService.updateMailboxStatus(accessCodeRequestDTO.getMailboxId(), "FREE");
        }

        accessCode.setUpdateOn(LocalDateTime.now());
        accessCode.setAccessCodeStatus(accessCodeStatus);

        return accessCodeRepository.save(accessCode);
    }
}
