package com.f5.buzon_inteligente_BE.locker;


import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


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

}
