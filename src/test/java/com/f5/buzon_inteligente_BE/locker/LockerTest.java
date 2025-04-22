package com.f5.buzon_inteligente_BE.locker;


import org.junit.jupiter.api.BeforeEach;


class LockerTest {

    private Locker locker;
    private LockerStatus lockerStatus;

    @BeforeEach
    void setUp() {
        lockerStatus = new LockerStatus();
        locker = new Locker("Calle 123", 48L, lockerStatus);
    } 

}
