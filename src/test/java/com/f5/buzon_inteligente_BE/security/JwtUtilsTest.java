package com.f5.buzon_inteligente_BE.security;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.http.HttpServletRequest;

@DisplayName("Unit tests for JwtUtils class")
public class JwtUtilsTest {

    @InjectMocks
    private JwtUtils jwtUtils;

    @Mock
    private HttpServletRequest httpServletRequest;

    private final String username = "testUser";
    private final String role = "USER";
    private final String dni = "12345678A";
    private final String bearerToken = "Bearer test.jwt.token";
    private final String expectedToken = "test.jwt.token";

    private String validToken;

    @BeforeEach
    @DisplayName("Initialize JwtUtils and common test data")
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtUtils, "jwtSecret",
                "mZBZfP8L99Zm8GDZCBoBPXaGNsq4CPGK/c/pX9nuSUXwxLBzME2YkdE+5EYXPLkP54x32MmNljQIgGhD4oM3Hg==");
        ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 3600000);

        validToken = jwtUtils.generateJwtToken(username, role, dni);
    }

    @Test
    @DisplayName("Should generate a non-null JWT token")
    public void testShouldGenerateNonNullJwtToken() {
        String token = jwtUtils.generateJwtToken(username, role, dni);
        assertNotNull(token, "Generated token should not be null");
    }

    @Test
    @DisplayName("Should validate JWT token successfully")
    public void testShouldValidateJwtTokenSuccessfully() {
        assertTrue(jwtUtils.validateJwtToken(validToken), "Token should be valid");
    }

    @Test
    @DisplayName("Should extract username and claims from JWT token")
    public void testShouldExtractUsernameAndClaimsFromJwtToken() {
        String extractedUsername = jwtUtils.getUserNameFromJwtToken(validToken);
        assertEquals(username, extractedUsername, "Extracted username should match");

        Claims claims = jwtUtils.getAllClaimsFromToken(validToken);
        assertEquals(role, claims.get("role"), "Role claim should match");
        assertEquals(dni, claims.get("dni"), "DNI claim should match");
    }

    
}

