package com.f5.buzon_inteligente_BE.parcel;

import com.f5.buzon_inteligente_BE.accesscode.AccessCode;
import com.f5.buzon_inteligente_BE.locker.Locker;
import com.f5.buzon_inteligente_BE.mailbox.Mailbox;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
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
    @DisplayName("Should return correct parcelId")
    void testShouldGetParcelId() throws Exception {

        Field field = Parcel.class.getDeclaredField("parcelId");
        field.setAccessible(true);
        field.set(parcel, 123L);

        assertEquals(123L, parcel.getParcelId());
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

    @Test
    @DisplayName("Should set alarmDate equal to deadlineDate when alarmHours is zero")
    void testShouldSetAlarmDateWithZero() {
        parcel.setAlarmDate(0L);
        assertEquals(parcel.getDeadlineDate(), parcel.getAlarmDate());
    }

    @Test
    @DisplayName("Should set alarmDate after deadlineDate if alarmHours is negative")
    void testShouldSetAlarmDateWithNegativeHours() {
        parcel.setAlarmDate(-2L);
        assertEquals(parcel.getDeadlineDate().plusHours(2), parcel.getAlarmDate());
    }

    @Test
    @DisplayName("Should set deadlineDate equal to deliveryDate when time limit is zero")
    void testShouldSetDeadlineDateWithZero() {
        when(locker.getTimeLimit()).thenReturn(0L);
        parcel = new Parcel(accessCode, mailbox, now, now.plusHours(1), now.plusHours(2));
        parcel.setDeadlineDate(locker);

        assertEquals(parcel.getDeliveryDate().withNano(0), parcel.getDeadlineDate().withNano(0));
    }

    @Test
    @DisplayName("Should set deadlineDate before deliveryDate when time limit is negative")
    void testShouldSetDeadlineDateWithNegativeLimit() {
        when(locker.getTimeLimit()).thenReturn(-3L);
        parcel = new Parcel(accessCode, mailbox, now, now.plusHours(1), now.plusHours(2));
        parcel.setDeadlineDate(locker);

        assertEquals(parcel.getDeliveryDate().minusHours(3).withNano(0), parcel.getDeadlineDate().withNano(0));
    }
}
