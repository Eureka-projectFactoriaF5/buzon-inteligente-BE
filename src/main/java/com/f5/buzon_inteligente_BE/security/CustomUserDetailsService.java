package com.f5.buzon_inteligente_BE.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.f5.buzon_inteligente_BE.user.User;
import com.f5.buzon_inteligente_BE.user.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByUserEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado: " + email));

        String role = "USER";

        return new CustomUserDetails(
                user.getUserId(),
                user.getUserEmail(),
                user.getUserPassword(),
                role,
                user.getUserDni(),
                true);
    }
}