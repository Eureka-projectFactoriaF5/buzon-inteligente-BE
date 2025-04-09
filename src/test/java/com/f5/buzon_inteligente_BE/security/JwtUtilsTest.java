package com.f5.buzon_inteligente_BE.security;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;

@DisplayName("Unit tests for JwtUtils class")
public class JwtUtilsTest {

    @InjectMocks
    private JwtUtils jwtUtils;

    @Mock
    private HttpServletRequest httpServletRequest;

    @DisplayName("Initialize JwtUtils and mock dependencies")
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtUtils, "jwtSecret",
                "mZBZfP8L99Zm8GDZCBoBPXaGNsq4CPGK/c/pX9nuSUXwxLBzME2YkdE+5EYXPLkP54x32MmNljQIgGhD4oM3Hg==");
        ReflectionTestUtils.setField(jwtUtils, "jwtExpirationMs", 3600000);
    }

    @Test
    @DisplayName("Should generate a non-null JWT token")
    public void test_Generate_JWT_Token() {
        String token = jwtUtils.generateJwtToken("testUser", "USER", "1234578A");

        assertNotNull(token, "Generated token should not be null");
    }

}
