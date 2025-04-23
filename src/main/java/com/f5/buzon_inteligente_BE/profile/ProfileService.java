package com.f5.buzon_inteligente_BE.profile;

import com.f5.buzon_inteligente_BE.profile.DTO.UserProfileResponseDTO;
import com.f5.buzon_inteligente_BE.user.User;
import com.f5.buzon_inteligente_BE.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.f5.buzon_inteligente_BE.profile.DTO.ProfileUpdateRequestDTO;

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
    public UserProfileResponseDTO updateUserFromProfile(Long userId, UserProfileUpdateRequestDTO dto) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + userId));
    
        user.setUserDni(dto.getUserDni());
        user.setUserName(dto.getUserName());
        user.setUserSurname(dto.getUserSurname());
        user.setUserEmail(dto.getUserEmail());
    
        if (dto.getUserPassword() != null && !dto.getUserPassword().isEmpty()) {
            String encodedPassword = passwordEncoder.encode(dto.getUserPassword());
            user.setUserPassword(encodedPassword);
        }
    
        User updatedUser = userRepository.save(user);
    
        Profile profile = profileRepository.findByUserUserId(userId)
                .orElseThrow(() -> new RuntimeException("Perfil no encontrado para el usuario"));
    
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
