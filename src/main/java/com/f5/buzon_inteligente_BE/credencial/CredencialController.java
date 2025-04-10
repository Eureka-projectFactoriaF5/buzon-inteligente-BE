package com.f5.buzon_inteligente_BE.credencial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



public class CredencialController {

    
    @Autowired
    private CredencialService credencialService;

    @PutMapping("/permanent/{userId}")
    public ResponseEntity<String> updateCredential(
            @PathVariable Long userId,
            @RequestBody CredentialRequest request) {
        try {
            boolean updated = credencialService.updatePermanentCredential(userId, request);
            return ResponseEntity.ok("Credential updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

}
