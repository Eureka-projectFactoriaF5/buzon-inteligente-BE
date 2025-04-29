package com.f5.buzon_inteligente_BE.mailbox;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.BeforeEach;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.lang.reflect.Field;

public class MailboxSizeTest {
    
    private MailboxSize mailboxSize;

    @BeforeEach
    void setUp() throws NoSuchFieldException, IllegalAccessException {
        mailboxSize = new MailboxSize("LARGE", 10);
        Field mailboxSizeId = MailboxSize.class.getDeclaredField("mailboxSizeId");
        mailboxSizeId.setAccessible(true);
        mailboxSizeId.set(mailboxSize, 1L);
    }

    @Test
    @DisplayName("getMailboxSizeId test")
    void testGetMailboxSizeId() {
        assertThat(mailboxSize.getMailboxSizeId(), is(1L));    
    }

    @Test
    @DisplayName("setMailboxSizeId test")
    void testGetMailboxSizeName() {
        assertThat(mailboxSize.getMailboxSizeName(), is("LARGE"));
    }

    @Test
    @DisplayName("setMailboxSizeName test")
    void testSetMailboxSizeName() {
        mailboxSize.setMailboxSizeName("SMALL");
        assertThat(mailboxSize.getMailboxSizeName(), is("SMALL"));  
    }

    @Test
    @DisplayName("getCapacity test")
    void testGetCapacity() {
        assertThat(mailboxSize.getCapacity(), is(10));
    }

    @Test
    @DisplayName("setCapacity test")
    void testSetCapacity() {
        mailboxSize.setCapacity(20);
        assertThat(mailboxSize.getCapacity(), is(20));
    }
}
