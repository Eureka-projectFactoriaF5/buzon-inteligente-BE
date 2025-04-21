package com.f5.buzon_inteligente_BE.profile;

import com.f5.buzon_inteligente_BE.user.User;
import com.f5.buzon_inteligente_BE.user.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private ProfileService profileService;

    @Test
    @DisplayName("Should return all profiles")
    void testShouldGetAllProfiles_returnsProfiles() {
        List<Profile> profiles = List.of(new Profile(), new Profile());
        when(profileRepository.findAll()).thenReturn(profiles);

        List<Profile> result = profileService.getAllProfiles();

        assertEquals(2, result.size());
        verify(profileRepository).findAll();
    }

    @Test
    @DisplayName("Should return profile by ID when it exists")
    void testShouldGetProfileById_found() {
        Profile profile = new Profile();
        when(profileRepository.findById(1L)).thenReturn(Optional.of(profile));

        Optional<Profile> result = profileService.getProfileById(1L);

        assertTrue(result.isPresent());
        assertEquals(profile, result.get());
    }

    @Test
    @DisplayName("Should return empty Optional when profile by ID does not exist")
    void testShouldGetProfileById_notFound() {
        when(profileRepository.findById(1L)).thenReturn(Optional.empty());

        Optional<Profile> result = profileService.getProfileById(1L);

        assertTrue(result.isEmpty());
    }

    @Test
    @DisplayName("Should return profile by userId when it exists")
    void testShouldGetProfileByUserId_found() {
        Profile profile = new Profile();
        when(profileRepository.findByUserUserId(1L)).thenReturn(Optional.of(profile));

        Optional<Profile> result = profileService.getProfileByUserId(1L);

        assertTrue(result.isPresent());
        assertEquals(profile, result.get());
        verify(profileRepository).findByUserUserId(1L);
    }

    @Test
    @DisplayName("Should return empty Optional when profile by userId does not exist")
    void testShouldGetProfileByUserId_notFound() {
        when(profileRepository.findByUserUserId(1L)).thenReturn(Optional.empty());

        Optional<Profile> result = profileService.getProfileByUserId(1L);

        assertTrue(result.isEmpty());
        verify(profileRepository).findByUserUserId(1L);
    }

    @Test
    @DisplayName("Should return profile by permanentCredential when it exists")
    void testShouldGetProfileByPermanentCredential_found() {
        Profile profile = new Profile();
        when(profileRepository.findByPermanentCredential("abc-123")).thenReturn(Optional.of(profile));

        Optional<Profile> result = profileService.getProfileByPermanentCredential("abc-123");

        assertTrue(result.isPresent());
        assertEquals(profile, result.get());
        verify(profileRepository).findByPermanentCredential("abc-123");
    }

    @Test
    @DisplayName("Should return empty Optional when profile by permanentCredential does not exist")
    void testShouldGetProfileByPermanentCredential_notFound() {
        when(profileRepository.findByPermanentCredential("non-existent")).thenReturn(Optional.empty());

        Optional<Profile> result = profileService.getProfileByPermanentCredential("non-existent");

        assertTrue(result.isEmpty());
        verify(profileRepository).findByPermanentCredential("non-existent");
    }

    @Test
    @DisplayName("Should create a new profile when user exists and profile does not")
    void testShouldCreateProfile_success() {
        User user = new User();
        Profile profile = new Profile(user);

        when(profileRepository.existsByUserUserId(1L)).thenReturn(false);
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(profileRepository.save(any(Profile.class))).thenReturn(profile);

        Profile result = profileService.createProfile(1L);

        assertNotNull(result);
        assertEquals(profile, result);
    }

    @Test
    @DisplayName("Should throw exception when profile already exists for user")
    void testShoulCreateProfile_alreadyExists_throwsException() {
        when(profileRepository.existsByUserUserId(1L)).thenReturn(true);

        RuntimeException exception = assertThrows(RuntimeException.class, () -> profileService.createProfile(1L));
        assertEquals("Profile already exists for user with id: 1", exception.getMessage());
    }
    
}
