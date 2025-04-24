package com.f5.buzon_inteligente_BE.profile;

import com.f5.buzon_inteligente_BE.profile.DTO.ProfileDTO;
import com.f5.buzon_inteligente_BE.profile.DTO.ProfileUpdateRequestDTO;
import com.f5.buzon_inteligente_BE.profile.DTO.UserProfileResponseDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/profile")
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
    public ResponseEntity<UserProfileResponseDTO> getProfileByUserId(@PathVariable Long userId) {
        return profileService.getProfileByUserId(userId)
                .map(profile -> UserProfileResponseDTO.fromEntities(profile.getUser(), profile))
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/user/{userId}")
    public ResponseEntity<UserProfileResponseDTO> updateUserFromProfile(
            @PathVariable Long userId,
            @RequestBody ProfileUpdateRequestDTO request) {
        UserProfileResponseDTO updated = profileService.updateUserFromProfile(userId, request);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfile(@PathVariable Long id) {
        profileService.deleteProfile(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/regenerate-credential")
    public ResponseEntity<String> regenerateCredential(@PathVariable Long id) {
        String newCredential = profileService.regeneratePermanentCredential(id);
        return ResponseEntity.ok(newCredential);
    }
}
