package com.f5.buzon_inteligente_BE.accesscode;

import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.f5.buzon_inteligente_BE.locker.Locker;
import com.f5.buzon_inteligente_BE.profile.Profile;
import com.f5.buzon_inteligente_BE.user.User;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccessCodeValidationService {
    private final AccessCodeRepository accessCodeRepository;

    public AccessCodeValidationService(AccessCodeRepository accessCodeRepository) {
        this.accessCodeRepository = accessCodeRepository;
    }


    @Transactional(readOnly = true)
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
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "message", "The access code is not associated with any profile"
            ));
        }

        User user = profile.getUser();
        if(user == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "message", "The profile is not associated with any user"
            ));
        }

        Locker locker = user.getLocker();
        if(locker == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
                "message", "The user is not associated with any locker"
            ));
        }

        if(!"AVAILABLE".equalsIgnoreCase(locker.getLockerStatus().getLockerStatusName())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(Map.of(
            "message", "The locker is not active"
            ));
        }

        return ResponseEntity.ok(Map.of(
            "message", "Access code is valid",
            "accessCodeId", accessCode.getAccessCodeId()
        ));
    }
    
}
