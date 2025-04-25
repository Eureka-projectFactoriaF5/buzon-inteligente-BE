package com.f5.buzon_inteligente_BE.mailbox.exceptions;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class MailboxNotFoundException extends RuntimeException {
    public MailboxNotFoundException(String message) {
        super(message);
    }
}