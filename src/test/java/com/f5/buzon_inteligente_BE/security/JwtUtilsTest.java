package com.f5.buzon_inteligente_BE.security;

import org.aspectj.lang.annotation.Before;
import org.aspectj.util.Reflection;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.util.ReflectionTestUtils;

import jakarta.servlet.http.HttpServletRequest;


public class JwtUtilsTest {    

    @InjectMocks
    private JwtUtils jwtUtils;

    @Mock
    private HttpServletRequest httpServletRequest;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(jwtUtils, "jwtSecret", "Zm9vYmFyYmF6cXV4eHl6c2VjcmV0a2V5MTIzNDU2");
        ReflectionTestUtils.setField(jwtUtils, "jwExpirationMs", 3600000);
    }


}
