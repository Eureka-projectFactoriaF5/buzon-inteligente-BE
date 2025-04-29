package com.f5.buzon_inteligente_BE.config;

import com.f5.buzon_inteligente_BE.auth.logout.LogoutService;
import com.f5.buzon_inteligente_BE.security.CustomUserDetailsService;
import com.f5.buzon_inteligente_BE.security.JwtUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.userdetails.DaoAuthenticationConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@DisplayName("Unit tests for SecurityConfig")
class SecurityConfigTest {

    @Mock
    private CustomUserDetailsService customUserDetailsService;

    @Mock
    private JwtUtils jwtUtils;

    @Mock
    private LogoutService logoutService;

    private SecurityConfig securityConfig;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        securityConfig = new SecurityConfig(customUserDetailsService, jwtUtils, logoutService);
    }

    @Test
    @DisplayName("Should return an instance of BCryptPasswordEncoder")
    void testPasswordEncoderShouldReturnBCryptPasswordEncoder() {
        PasswordEncoder encoder = securityConfig.passwordEncoder();
        assertThat(encoder).isInstanceOf(org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder.class);
    }

    @Test
    @DisplayName("Should successfully build SecurityFilterChain")
    void testSecurityFilterChainShouldBuildSuccessfully() throws Exception {
        HttpSecurity http = mock(HttpSecurity.class, RETURNS_DEEP_STUBS);

        SecurityFilterChain filterChain = securityConfig.securityFilterChain(http);
        assertThat(filterChain).isNotNull();
    }

    @Test
    @DisplayName("Should successfully build AuthenticationManager with custom UserDetailsService and PasswordEncoder")
    void testAuthenticationManagerShouldBuildSuccessfully() throws Exception {
        HttpSecurity http = mock(HttpSecurity.class, RETURNS_DEEP_STUBS);
        AuthenticationManagerBuilder builder = mock(AuthenticationManagerBuilder.class);
        @SuppressWarnings("unchecked")
        DaoAuthenticationConfigurer<AuthenticationManagerBuilder, CustomUserDetailsService> daoConfigurer = mock(DaoAuthenticationConfigurer.class);

        when(http.getSharedObject(AuthenticationManagerBuilder.class)).thenReturn(builder);
        when(builder.userDetailsService(customUserDetailsService)).thenReturn(daoConfigurer);
        when(daoConfigurer.passwordEncoder(any(PasswordEncoder.class))).thenReturn(daoConfigurer);
        when(builder.build()).thenReturn(mock(AuthenticationManager.class));

        AuthenticationManager authenticationManager = securityConfig.authenticationManager(http, securityConfig.passwordEncoder());

        assertThat(authenticationManager).isNotNull();

        verify(builder).userDetailsService(customUserDetailsService);
        verify(daoConfigurer).passwordEncoder(any(PasswordEncoder.class));
    }
}

