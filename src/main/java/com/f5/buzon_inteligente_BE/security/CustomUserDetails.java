package com.f5.buzon_inteligente_BE.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.List;

public class CustomUserDetails implements UserDetails {

    private final String username;
    private final String password;
    private final String role;
    private final String dni;
    private final boolean enabled;

    public CustomUserDetails(String username, String password, String role, String dni, boolean enabled) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.dni = dni;
        this.enabled = enabled;
    }

    public String getRole() {
        return role;
    }

    public String getDni() {
        return dni;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_" + role));
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}