package com.f5.buzon_inteligente_BE.profile;

import com.f5.buzon_inteligente_BE.profile.DTO.ProfileUpdateRequestDTO;
import com.f5.buzon_inteligente_BE.profile.DTO.UserProfileResponseDTO;
import com.f5.buzon_inteligente_BE.profile.exception.ProfileNotFoundException;
import com.f5.buzon_inteligente_BE.profile.exception.UserNotFoundException;
import com.f5.buzon_inteligente_BE.user.User;
import com.f5.buzon_inteligente_BE.user.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.f5.buzon_inteligente_BE.auth.register.RegisterExceptions.RegisterException;


import java.util.List;
import java.util.Optional;
import java.util.UUID;

import java.util.Base64;
import java.util.Base64.Decoder;

@Service
public class ProfileService {

    private final ProfileRepository profileRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public ProfileService(ProfileRepository profileRepository, UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.profileRepository = profileRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
                .orElseThrow(() -> new UserNotFoundException(userId));

        Profile profile = new Profile(user);
        return profileRepository.save(profile);
    }

    @Transactional
    public UserProfileResponseDTO updateUserFromProfile(Long userId, ProfileUpdateRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException(userId));

        user.setUserDni(dto.getUserDni());
        user.setUserName(dto.getUserName());
        user.setUserSurname(dto.getUserSurname());
        user.setUserEmail(dto.getUserEmail());

        if (dto.getUserPassword() != null && !dto.getUserPassword().isEmpty()) {
        String decodedPassword; 
        try {
            Decoder decoder = Base64.getDecoder();
            byte[] decodedBytes = decoder.decode(dto.getUserPassword());
            decodedPassword = new String(decodedBytes);
        } catch (Exception e) {
            throw new RegisterException("Error decoding password from Base64", e);
        }    
            String encodedPassword = passwordEncoder.encode(dto.getUserPassword());
            user.setUserPassword(encodedPassword);
        }

        User updatedUser = userRepository.save(user);

        Profile profile = profileRepository.findByUserUserId(userId)
                .orElseThrow(() -> new ProfileNotFoundException(userId));

        return UserProfileResponseDTO.fromEntities(updatedUser, profile);
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
