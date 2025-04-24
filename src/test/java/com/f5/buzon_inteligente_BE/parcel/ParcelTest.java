package com.f5.buzon_inteligente_BE.parcel;

import com.f5.buzon_inteligente_BE.accesscode.AccessCode;
import com.f5.buzon_inteligente_BE.locker.Locker;
import com.f5.buzon_inteligente_BE.mailbox.Mailbox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ParcelTest {

    private AccessCode accessCode;
    private Mailbox mailbox;
    private Locker locker;
    private LocalDateTime now;
    private Parcel parcel;

    @BeforeEach
    void setUp() {
        accessCode = mock(AccessCode.class);
        mailbox = mock(Mailbox.class);
        locker = mock(Locker.class);
        now = LocalDateTime.now();
        parcel = new Parcel(accessCode, mailbox, now.plusHours(1), now.plusHours(2), now.plusHours(3));
    }

    @Test
    @DisplayName("Should return correct access code")
    void testShouldGetAccessCode() {
        assertEquals(accessCode, parcel.getAccessCode());
    }

    @Test
    @DisplayName("Should return correct mailbox")
    void testShouldGetMailbox() {
        assertEquals(mailbox, parcel.getMailbox());
    }

    @Test
    @DisplayName("Should return correct alarm and deadline dates, and deliveryDate is set to now")
    void testShouldDates() {
        LocalDateTime before = LocalDateTime.now().minusSeconds(1);
        parcel = new Parcel(accessCode, mailbox, now.plusHours(1), now.plusHours(2), now.plusHours(3));
        LocalDateTime after = LocalDateTime.now().plusSeconds(1);

        assertNotNull(parcel.getDeliveryDate());
        assertTrue(parcel.getDeliveryDate().isAfter(before) && parcel.getDeliveryDate().isBefore(after),
                "Delivery date should be within expected range of now");

        assertEquals(now.plusHours(2), parcel.getAlarmDate());
        assertEquals(now.plusHours(3), parcel.getDeadlineDate());
    }

    @Test
    @DisplayName("Should set alarm date based on deadline minus alarm hours")
    void testShouldSetAlarmDate() {
        parcel.setAlarmDate(5L);
        
        assertEquals(parcel.getDeadlineDate().minusHours(5), parcel.getAlarmDate());
    }

    @Test
    @DisplayName("Should set deadline date based on delivery date plus locker time limit")
    void testShouldSetDeadlineDate() {
        when(locker.getTimeLimit()).thenReturn(6L);

        parcel = new Parcel(accessCode, mailbox, now, now.plusHours(1), now.plusHours(2));
        parcel.setDeadlineDate(locker);

        assertEquals(
                parcel.getDeadlineDate().withNano(0),
                parcel.getDeliveryDate().plusHours(6).withNano(0));
    }
}
