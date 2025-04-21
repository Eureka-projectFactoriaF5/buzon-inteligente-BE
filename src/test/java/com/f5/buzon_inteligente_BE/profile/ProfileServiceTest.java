package com.f5.buzon_inteligente_BE.profile;

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

}
