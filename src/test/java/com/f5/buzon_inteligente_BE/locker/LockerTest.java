package com.f5.buzon_inteligente_BE.locker;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.f5.buzon_inteligente_BE.mailbox.Mailbox;
import com.f5.buzon_inteligente_BE.user.User;

class LockerTest {

    private Locker locker;
    private LockerStatus lockerStatus;

    @BeforeEach
    void setUp() {
        lockerStatus = new LockerStatus();
        locker = new Locker("Calle 123", 48L, lockerStatus);
    }

    @Test
    @DisplayName("Should constructor set address, timeLimit and lockerStatus correctly")
    void testShouldConstructor() {
        assertThat(locker.getAddress()).isEqualTo("Calle 123");
        assertThat(locker.getTimeLimit()).isEqualTo(48L);
        assertThat(locker.getLockerStatus()).isEqualTo(lockerStatus);
    }

    @Test
    @DisplayName("Should Setters and Getters  work correctly")
    void testShouldSettersAndGetters() {
        locker.setAddress("Nueva dirección");
        locker.setTimeLimit(72L);
        LockerStatus newStatus = new LockerStatus();
        locker.setLockerStatus(newStatus);

        assertThat(locker.getAddress()).isEqualTo("Nueva dirección");
        assertThat(locker.getTimeLimit()).isEqualTo(72L);
        assertThat(locker.getLockerStatus()).isEqualTo(newStatus);
    }

    @Test
    @DisplayName("Should mailboxes list be modifiable")
    void testShouldAddMailbox() {
        Mailbox mailbox = new Mailbox();
        locker.getMailboxes().add(mailbox);

        List<Mailbox> mailboxes = locker.getMailboxes();
        assertThat(mailboxes).hasSize(1);
        assertThat(mailboxes.get(0)).isEqualTo(mailbox);
    }

    @Test
    @DisplayName("Should users list be modifiable")
    void testShouldAddUser() {
        User user = new User();
        locker.getUsers().add(user);

        List<User> users = locker.getUsers();
        assertThat(users).hasSize(1);
        assertThat(users.get(0)).isEqualTo(user);
    }

}
