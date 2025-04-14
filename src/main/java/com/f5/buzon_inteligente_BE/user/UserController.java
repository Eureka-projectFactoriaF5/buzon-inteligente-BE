package com.f5.buzon_inteligente_BE.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.f5.buzon_inteligente_BE.profile.dto.CredentialRequest;

@RestController
@RequestMapping("/api/credential")
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/permanent/{userId}")
    public ResponseEntity<String> updateCredential(
            @PathVariable Long userId,
            @RequestBody CredentialRequest request) {
        try {
            boolean updated = userService.updatePermanentCredential(userId, request);
            return ResponseEntity.ok("Credential updated successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
