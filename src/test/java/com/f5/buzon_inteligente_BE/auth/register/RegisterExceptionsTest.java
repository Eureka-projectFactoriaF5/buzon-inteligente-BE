package com.f5.buzon_inteligente_BE.auth.register;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.f5.buzon_inteligente_BE.auth.register.RegisterExceptions.DniAlreadyExistsException;
import com.f5.buzon_inteligente_BE.auth.register.RegisterExceptions.EmailAlreadyExistsException;
import com.f5.buzon_inteligente_BE.auth.register.RegisterExceptions.RegisterException;

public class RegisterExceptionsTest {
    @Test
    @DisplayName("RegisterException test case")
    void testException() {
        RegisterExceptions registerExceptions = new RegisterExceptions();
        assertNotNull(registerExceptions);        
    }
    @Test
    @DisplayName("RegisterException email already exists test case")
    void testEmailAlreadyExistsException() {
        EmailAlreadyExistsException registerException = new EmailAlreadyExistsException("message");
        assertEquals("message", registerException.getMessage());
    }
    @Test
    @DisplayName("RegisterException dni already exists test case")
    void testDniAlreadyExistsException() {
        DniAlreadyExistsException registerException = new DniAlreadyExistsException("message");
        assertEquals("message", registerException.getMessage());
    }
    @Test
    @DisplayName("RegisterException test case")
    void testRegisterException() {
        RegisterException registerException = new RegisterException("message");
        assertEquals("message", registerException.getMessage());
    }
    @Test
    @DisplayName("RegisterException with cause test case")
    void testRegisterExceptionWithCause() {
        Throwable cause = new RuntimeException("Causa original");
        RegisterException registerException = new RegisterException("message", cause);
        assertEquals("message", registerException.getMessage());
        assertEquals(cause, registerException.getCause());
    }
}
