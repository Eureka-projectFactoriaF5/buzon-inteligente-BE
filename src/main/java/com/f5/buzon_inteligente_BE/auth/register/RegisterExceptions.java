package com.f5.buzon_inteligente_BE.auth.register;

public class RegistrationExceptions {

    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class RegistrationException extends RuntimeException {
        public RegistrationException(String message) {
            super(message);
        }
        
        public RegistrationException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
