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


@RestController
@RequestMapping("/api/accesscode")
public class AccessCodeController {

    private final AccessCodeService accessCodeService;

    public AccessCodeController(AccessCodeService accessCodeService) {
        this.accessCodeService = accessCodeService;
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
    @PutMapping("/{accessCodeId}")
    public ResponseEntity<AccessCodeUpdateStatusResponseDTO> setAccessCodeStatusId(@PathVariable Long accessCodeId, @RequestBody AccessCodeUpdateStatusRequestDTO accessCodeRequestDTO) {
        AccessCode updatedAccessCode = accessCodeService.updateAccessCodeStatus(accessCodeId, accessCodeRequestDTO);
        return new ResponseEntity<>(
                AccessCodeUpdateStatusResponseDTO.fromEntities(updatedAccessCode),
                HttpStatus.OK);
    }
}
