package com.f5.buzon_inteligente_BE.mailbox.exceptions;

public class MailboxNotFoundException extends RuntimeException {
    public MailboxNotFoundException(String message) {
        super(message);
    }
}