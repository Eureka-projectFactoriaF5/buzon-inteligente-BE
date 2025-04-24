package com.f5.buzon_inteligente_BE.accesscode;

import java.util.List;
import java.util.stream.Collectors;

import com.f5.buzon_inteligente_BE.accesscode.DTO.AccessCodeRequestDTO;
import com.f5.buzon_inteligente_BE.accesscode.DTO.AccessCodeResponseDTO;

import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accesscode")
public class AccessCodeController {

    private final AccessCodeService accessCodeService;
    private final AccessCodeValidationService accessCodeValidationService;

    public AccessCodeController(AccessCodeService accessCodeService, AccessCodeValidationService accessCodeValidationService) {
        this.accessCodeService = accessCodeService;
        this.accessCodeValidationService = accessCodeValidationService;
    }

    @PostMapping
    public ResponseEntity<AccessCodeResponseDTO> createAccessCode(
            @RequestBody AccessCodeRequestDTO accessCodeRequestDTO) {
        AccessCode createdAccessCode = accessCodeService.createAccessCode(accessCodeRequestDTO);
        return new ResponseEntity<>(
                AccessCodeResponseDTO.fromEntities(createdAccessCode),
                HttpStatus.CREATED);
    }

    @GetMapping("/profile/{profileId}")
    public ResponseEntity<List<AccessCodeResponseDTO>> getAccessCodesByProfile(@PathVariable Long profileId) {
        List<AccessCode> accessCodes = accessCodeService.getAccessCodesByProfileId(profileId);
        List<AccessCodeResponseDTO> accessCodeDTOs = accessCodes.stream()
                .map(AccessCodeResponseDTO::fromEntities)
                .collect(Collectors.toList());
        return ResponseEntity.ok(accessCodeDTOs);
    }

    @GetMapping("/validate")
    public ResponseEntity<?> validateAccessCode(@RequestParam String code){
        return accessCodeValidationService.validateAccessCode(code);
    }
}
