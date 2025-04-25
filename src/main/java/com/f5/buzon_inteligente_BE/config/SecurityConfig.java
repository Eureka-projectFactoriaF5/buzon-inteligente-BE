package com.f5.buzon_inteligente_BE.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.f5.buzon_inteligente_BE.auth.logout.LogoutService;
import com.f5.buzon_inteligente_BE.security.CustomUserDetailsService;
import com.f5.buzon_inteligente_BE.security.JwtAuthenticationFilter;
import com.f5.buzon_inteligente_BE.security.JwtUtils;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtils jwtUtils;
    private final LogoutService logoutService;

    public SecurityConfig(CustomUserDetailsService customUserDetailsService, JwtUtils jwtUtils, LogoutService logoutService) {
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtils = jwtUtils;
        this.logoutService = logoutService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            .cors(cors -> Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authorizeHttpRequests(auth -> auth

            .requestMatchers("/api/users/**").authenticated()
            .requestMatchers("/api/profiles/**").authenticated()
            .requestMatchers("/api/accesscode/**").authenticated()
            
            .anyRequest().permitAll())
            .addFilterBefore(new JwtAuthenticationFilter(jwtUtils, customUserDetailsService, logoutService),
            UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http, PasswordEncoder passwordEncoder) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder
            .userDetailsService(customUserDetailsService)
            .passwordEncoder(passwordEncoder);
        return builder.build();
    }

}
