package com.f5.buzon_inteligente_BE.mailbox;

import java.lang.reflect.Field;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MailboxStatusTest {

    MailboxStatus mailboxStatus;
    @BeforeEach
    public void setup() throws NoSuchFieldException, SecurityException, IllegalAccessException  {
        mailboxStatus = new MailboxStatus("FREE");
        Field mailboxStatusIdField = MailboxStatus.class.getDeclaredField("mailboxStatusId");
        mailboxStatusIdField.setAccessible(true);
        mailboxStatusIdField.set(mailboxStatus, 1L);
        
    }
    @Test
    @DisplayName("getMailboxStatusId test")
    public void getMailboxStatusIdTest() {
        assertThat(mailboxStatus.getMailboxStatusId()).isEqualTo(1L);
    }

    @Test
    @DisplayName("getMailboxStatusName test")
    public void getMailboxStatusNameTest() {
        assertThat(mailboxStatus.getMailboxStatusName()).isEqualTo("FREE");
    }

    @Test
    @DisplayName("setMailboxStatusName test")
    public void setMailboxStatusNameTest() {
        mailboxStatus.setMailboxStatusName("USED");
        assertThat(mailboxStatus.getMailboxStatusName()).isEqualTo("USED");
    }

}
