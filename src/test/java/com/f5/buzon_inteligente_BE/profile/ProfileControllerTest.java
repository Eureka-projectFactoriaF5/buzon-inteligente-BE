package com.f5.buzon_inteligente_BE.profile;

import com.f5.buzon_inteligente_BE.security.JwtUtils;
import com.f5.buzon_inteligente_BE.user.User;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import org.mockito.Mockito;


import static org.mockito.Mockito.when;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
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
}
