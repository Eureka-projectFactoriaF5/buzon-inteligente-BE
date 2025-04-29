package com.f5.buzon_inteligente_BE.mailbox;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

import java.lang.reflect.Field;

import com.f5.buzon_inteligente_BE.locker.Locker;

public class MailboxTest {

    private Mailbox mailbox;
    private MailboxSize mailboxSize;
    private MailboxStatus mailboxStatus;
    private Locker locker;


    @BeforeEach
    void setUp() throws Exception {
        mailboxSize = new MailboxSize("LARGE", 10);
        mailboxStatus = new MailboxStatus("FREE");
        locker = new Locker();
        mailbox = new Mailbox(mailboxSize, locker, mailboxStatus, 42);

        Field mailboxIdField = Mailbox.class.getDeclaredField("mailboxId");
        mailboxIdField.setAccessible(true);
        mailboxIdField.set(mailbox, 1L);
    }

    @Test
    @DisplayName("GetLocker test")
    void testGetLocker() {
        assertThat(mailbox.getLocker(), is(locker));
    }

    @Test
    @DisplayName("GetMailboxId test")
    void testGetMailboxId() {
        assertThat(mailbox.getMailboxId(), is(1L));
    }

    @Test
    @DisplayName("GetMailboxNumber test")
    void testGetMailboxNumber() {
        assertThat(mailbox.getMailboxNumber(), is(42));
    }

    @Test
    @DisplayName("GetMailboxSize test")
    void testGetMailboxSize() {
        assertThat(mailbox.getMailboxSize(), is(mailboxSize));
    }

    @Test
    @DisplayName("GetMailboxStatus test")
    void testGetMailboxStatus() {
        assertThat(mailbox.getMailboxStatus(), is(mailboxStatus));
    }

    @Test
    void testSetMailboxNumber() {
        mailbox.setMailboxNumber(43);
        assertThat(mailbox.getMailboxNumber(), is(43));
    }

    @Test
    void testSetMailboxSize() {
        mailbox.setMailboxSize(new MailboxSize("SMALL", 1));
        assertThat(mailbox.getMailboxSize().getMailboxSizeName(), is("SMALL"));
    }

    @Test
    void testSetMailboxStatus() {
        mailbox.setMailboxStatus(new MailboxStatus("BUSY"));
        assertThat(mailbox.getMailboxStatus().getMailboxStatusName(), is("BUSY"));
    }
}
