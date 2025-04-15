package com.f5.buzon_inteligente_BE.profile;

import com.f5.buzon_inteligente_BE.user.User;
import com.f5.buzon_inteligente_BE.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public List<Profile> getAllProfiles() {
        return profileRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Profile> getProfileById(Long id) {
        return profileRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public Optional<Profile> getProfileByUserId(Long userId) {
        return profileRepository.findByUserUserId(userId);
    }

    @Transactional(readOnly = true)
    public Optional<Profile> getProfileByPermanentCredential(String permanentCredential) {
        return profileRepository.findByPermanentCredential(permanentCredential);
    }

    @Transactional
    public Profile createProfile(Long userId) {
   
        if (profileRepository.existsByUserUserId(userId)) {
            throw new RuntimeException("Profile already exists for user with id: " + userId);
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Profile profile = new Profile(user);
        return profileRepository.save(profile);
    }

    @Transactional
    public Profile updateProfile(Long id, String permanentCredential) {
        Profile existingProfile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
        
     
        if (profileRepository.existsByPermanentCredential(permanentCredential)) {
            throw new RuntimeException("Credential already exists: " + permanentCredential);
        }
        
        existingProfile.setPermanentCredential(permanentCredential);
        return profileRepository.save(existingProfile);
    }

    @Transactional
    public void deleteProfile(Long id) {
        if (!profileRepository.existsById(id)) {
            throw new RuntimeException("Profile not found with id: " + id);
        }
        profileRepository.deleteById(id);
    }

    @Transactional
    public String regeneratePermanentCredential(Long id) {
        Profile profile = profileRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Profile not found with id: " + id));
        String newCredential = UUID.randomUUID().toString();
        profile.setPermanentCredential(newCredential);
        profileRepository.save(profile);
        return newCredential;
    }
}
