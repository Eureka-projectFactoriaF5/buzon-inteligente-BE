package com.f5.buzon_inteligente_BE.auth.register;

public class RegisterExceptions {

    public static class EmailAlreadyExistsException extends RuntimeException {
        public EmailAlreadyExistsException(String message) {
            super(message);
        }
    }

    public static class RegisterException extends RuntimeException {
        public RegisterException(String message) {
            super(message);
        }
        
        public RegisterException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
