package com.f5.buzon_inteligente_BE.profile;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profiles")
public class ProfileController {

    private final ProfileService profileService;

    public ProfileController(ProfileService profileService) {
        this.profileService = profileService;
    }

    @GetMapping
    public ResponseEntity<List<ProfileDTO>> getAllProfiles() {
        List<ProfileDTO> profiles = profileService.getAllProfiles()
                .stream()
                .map(ProfileDTO::fromEntity)
                .collect(Collectors.toList());
        return ResponseEntity.ok(profiles);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileDTO> getProfileById(@PathVariable Long id) {
        return profileService.getProfileById(id)
                .map(ProfileDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<ProfileDTO> getProfileByUserId(@PathVariable Long userId) {
        return profileService.getProfileByUserId(userId)
                .map(ProfileDTO::fromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

   
    @PostMapping
    public ResponseEntity<ProfileDTO> createProfile(@RequestBody CreateProfileRequest request) {
        Profile createdProfile = profileService.createProfile(request.getUserId());
        return new ResponseEntity<>(ProfileDTO.fromEntity(createdProfile), HttpStatus.CREATED);
    }

    // Endpoint para actualizar la permanentCredential manualmente, si se requiere
    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> updateProfile(@PathVariable Long id, @RequestBody UpdateProfileRequest request) {
        Profile updatedProfile = profileService.updateProfile(id, request.getPermanentCredential());
        return ResponseEntity.ok(ProfileDTO.fromEntity(updatedProfile));
    }

    // Endpoint para regenerar la credencial permanente
    @PostMapping("/{id}/regenerate-credential")
    public ResponseEntity<String> regenerateCredential(@PathVariable Long id) {
        String newCredential = profileService.regeneratePermanentCredential(id);
        return ResponseEntity.ok(newCredential);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }


    public static class CreateProfileRequest {
        private Long userId;

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }
    }


    public static class UpdateProfileRequest {
        private String permanentCredential;

        public String getPermanentCredential() {
            return permanentCredential;
        }

        public void setPermanentCredential(String permanentCredential) {
            this.permanentCredential = permanentCredential;
        }
    }
}
