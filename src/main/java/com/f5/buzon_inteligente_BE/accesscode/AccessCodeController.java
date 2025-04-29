package com.f5.buzon_inteligente_BE.accesscode;

import java.util.List;
import java.util.stream.Collectors;

import com.f5.buzon_inteligente_BE.accesscode.DTO.AccessCodeRequestDTO;
import com.f5.buzon_inteligente_BE.accesscode.DTO.AccessCodeResponseDTO;
import com.f5.buzon_inteligente_BE.accesscode.DTO.AccessCodeUpdateStatusRequestDTO;
import com.f5.buzon_inteligente_BE.accesscode.DTO.AccessCodeUpdateStatusResponseDTO;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.f5.buzon_inteligente_BE.mailbox.Mailbox;

@RestController
@RequestMapping("/api/accesscode")
public class AccessCodeController {

    private final AccessCodeService accessCodeService;
    private final AccessCodeValidationService accessCodeValidationService;

    public AccessCodeController(AccessCodeService accessCodeService,
            AccessCodeValidationService accessCodeValidationService) {
        this.accessCodeService = accessCodeService;
        this.accessCodeValidationService = accessCodeValidationService;
    }

    @PostMapping
    public ResponseEntity<AccessCodeResponseDTO> createAccessCode(
            @RequestBody AccessCodeRequestDTO accessCodeRequestDTO) {
        AccessCode createdAccessCode = accessCodeService.createAccessCode(accessCodeRequestDTO);

        Mailbox mailbox = createdAccessCode.getParcels().isEmpty()
                ? null
                : createdAccessCode.getParcels().get(0).getMailbox();

        return new ResponseEntity<>(
                AccessCodeResponseDTO.fromEntities(createdAccessCode, mailbox),
                HttpStatus.CREATED);
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<AccessCodeResponseDTO>> getAccessCodesByProfile(@PathVariable Long profileId) {
        List<AccessCode> accessCodes = accessCodeService.getAccessCodesByProfileId(profileId);
        List<AccessCodeResponseDTO> accessCodeDTOs = accessCodes.stream()
                .map(ac -> {
                    Mailbox mailbox = ac.getParcels().isEmpty()
                            ? null
                            : ac.getParcels().get(0).getMailbox();
                    return AccessCodeResponseDTO.fromEntities(ac, mailbox);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(accessCodeDTOs);
    }

    @GetMapping("/credential/{permanentCredential}")
    public ResponseEntity<?> getAccessCodesByCredential(@PathVariable String permanentCredential) {
        try {
            List<AccessCodeResponseDTO> accessCodes = accessCodeService.getAccessCodesByCredential(permanentCredential);
            return new ResponseEntity<>(accessCodes, HttpStatus.OK);
        } catch (AccessCodeException e) {
            return new ResponseEntity<>("Credencial no válida. Intente nuevamente o contacte soporte.",
                    HttpStatus.UNAUTHORIZED);
        }
    }
    @PutMapping("/{accessCodeId}")
    public ResponseEntity<AccessCodeUpdateStatusResponseDTO> setAccessCodeStatusId(@PathVariable Long accessCodeId, @RequestBody AccessCodeUpdateStatusRequestDTO accessCodeRequestDTO) {
        AccessCode updatedAccessCode = accessCodeService.updateAccessCodeStatus(accessCodeId, accessCodeRequestDTO);
        return new ResponseEntity<>(
                AccessCodeUpdateStatusResponseDTO.fromEntities(updatedAccessCode),
                HttpStatus.OK);
    }
    @GetMapping("/validate")
    public ResponseEntity<?> validateAccessCode(@RequestParam String code) {
        return accessCodeValidationService.validateAccessCode(code);

    }
}
