package com.f5.buzon_inteligente_BE.accesscode;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.f5.buzon_inteligente_BE.profile.Profile;

import jakarta.transaction.Transactional;

public class AccessCodeValidationService {
    private final AccessCodeService accessCodeRepository;

    public AccessCodeValidationService(AccessCodeService accessCodeRepository) {
        this.accessCodeRepository = accessCodeRepository;
    }


    @Transactional
    public ResponseEntity<Map<String, Object>>validateAccessCode(String code){

        Optional<AccessCode> optionalAccessCode = accessCodeRepository.findByAccessCode(code);

        if(optionalAccessCode.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of(
                "message", "Access code not found"
            ));
        }

        AccessCode accessCode = optionalAccessCode.get();

        if(accessCode.isLocked()){
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of(
                "message", "The access code is blocked"
                ));
        }

        Profile profile = accessCode.getProfile();
        if(profile == null){
            
        }
    }
    
}
