package com.f5.buzon_inteligente_BE.accesscode;

import com.f5.buzon_inteligente_BE.accesscode.DTO.AccessCodeRequestDTO;
import com.f5.buzon_inteligente_BE.accesscode.DTO.AccessCodeResponseDTO;
import com.f5.buzon_inteligente_BE.profile.Profile;
import com.f5.buzon_inteligente_BE.profile.ProfileRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
public class AccessCodeService {

    private final AccessCodeRepository accessCodeRepository;
    private final AccessCodeStatusRepository statusRepository;
    private final ProfileRepository profileRepository;

    public AccessCodeService(AccessCodeRepository accessCodeRepository,
            AccessCodeStatusRepository statusRepository,
            ProfileRepository profileRepository) {
        this.accessCodeRepository = accessCodeRepository;
        this.statusRepository = statusRepository;
        this.profileRepository = profileRepository;
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

  
    @Transactional(readOnly = true)
    public List<AccessCodeResponseDTO> getAccessCodesByCredential(String permanentCredential) {
        Profile profile = profileRepository.findByPermanentCredential(permanentCredential)
                .orElseThrow(() -> new AccessCodeException("Credencial no válida."));

        return accessCodeRepository.findAllByProfile_Id(profile.getId()).stream()
                .filter(ac -> {
                    String status = ac.getAccessCodeStatus().getAccessCodeStatusName();
                    return status.equalsIgnoreCase("Entregado") || status.equalsIgnoreCase("Entregado parcial");
                })
                .map(AccessCodeResponseDTO::fromEntities)
                .toList();
    }
}
