package com.f5.buzon_inteligente_BE.profile.exception;

public class ProfileNotFoundException extends RuntimeException {
    public ProfileNotFoundException(Long userId) {
        super("Perfil no encontrado para el usuario con ID: " + userId);
    }
}
