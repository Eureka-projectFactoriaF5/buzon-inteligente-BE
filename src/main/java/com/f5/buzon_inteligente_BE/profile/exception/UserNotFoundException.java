package com.f5.buzon_inteligente_BE.profile.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Long id) {
        super("Usuario no encontrado con ID: " + id);
    }
}
