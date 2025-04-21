package com.f5.buzon_inteligente_BE.profile;

import com.f5.buzon_inteligente_BE.profile.ProfileController.CreateProfileRequest;
import com.f5.buzon_inteligente_BE.security.JwtUtils;
import com.f5.buzon_inteligente_BE.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProfileController.class)
@AutoConfigureMockMvc(addFilters = false)
@ActiveProfiles("test")
@DisplayName("ProfileController Test Suite")
class ProfileControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private ProfileService profileService;

    @MockitoBean
    private JwtUtils jwtUtils;

    @Autowired
    private ObjectMapper objectMapper;

    private Profile mockProfile;
    private User mockUser;

    @BeforeEach
    void setup() {
        mockUser = Mockito.mock(User.class);
        when(mockUser.getUserId()).thenReturn(1L);
        when(mockUser.getUserDni()).thenReturn("12345678A");
        when(mockUser.getUserName()).thenReturn("testUser");
        when(mockUser.getUserSurname()).thenReturn("Rivero");
        when(mockUser.getUserEmail()).thenReturn("user@example.com");

        mockProfile = new Profile();
        mockProfile.setId(1L);
        mockProfile.setUser(mockUser);
        mockProfile.setPermanentCredential("abc123");
    }

    @Test
    @DisplayName("Should return all profiles successfully")
    void testShouldGetAllProfiles() throws Exception {
        when(profileService.getAllProfiles()).thenReturn(List.of(mockProfile));

        mockMvc.perform(get("/api/profile"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].permanentCredential").value("abc123"));
    }

    @Test
    @DisplayName("Should return a profile by its ID")
    void testShouldGetProfileById() throws Exception {
        when(profileService.getProfileById(1L)).thenReturn(Optional.of(mockProfile));

        mockMvc.perform(get("/api/profile/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    @DisplayName("Should return 404 when profile does not exist")
    void testShouldReturnNotFoundWhenProfileNotExists() throws Exception {
        when(profileService.getProfileById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/profile/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    @DisplayName("Should return profile by user ID")
    void testShouldGetProfileByUserId() throws Exception {
        when(profileService.getProfileByUserId(1L)).thenReturn(Optional.of(mockProfile));

        mockMvc.perform(get("/api/profile/user/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userDni").value("12345678A"))
                .andExpect(jsonPath("$.userName").value("testUser"))
                .andExpect(jsonPath("$.userSurname").value("Rivero"))
                .andExpect(jsonPath("$.userEmail").value("user@example.com"))
                .andExpect(jsonPath("$.permanentCredential").value("abc123"));
    }

    @Test
    @DisplayName("Should create a profile successfully")
    void testShouldCreateProfile() throws Exception {
        CreateProfileRequest request = new CreateProfileRequest();
        request.setUserId(1L);

        when(profileService.createProfile(1L)).thenReturn(mockProfile);

        mockMvc.perform(post("/api/profile")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.userDni").value("12345678A"))
                .andExpect(jsonPath("$.userName").value("testUser"))
                .andExpect(jsonPath("$.userSurname").value("Rivero"))
                .andExpect(jsonPath("$.userEmail").value("user@example.com"))
                .andExpect(jsonPath("$.permanentCredential").value("abc123"));
    }
}
