package com.f5.buzon_inteligente_BE.accesscode;

import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;

import static org.assertj.core.api.Assertions.assertThat;

public class AccessCodeStatusTest {
    AccessCodeStatus accessCodeStatus;

    @BeforeEach
    void setUp() {
        accessCodeStatus = new AccessCodeStatus("Entregado");
    }

    @Test
    @DisplayName("test GetAccessCodeStatusId")
    void testGetAccessCodeStatusId() {
        ReflectionTestUtils.setField(accessCodeStatus, "accessCodeStatusId", 1L);

        assertThat(accessCodeStatus.getAccessCodeStatusId()).isNotNull();
    }

    @Test
    @DisplayName("test GetAccessCodeStatusName")
    void testGetAccessCodeStatusName() {
        assertThat(accessCodeStatus.getAccessCodeStatusName()).isEqualTo("Entregado");
    }

    @Test
    @DisplayName("test GetAccessCodes")
    void testGetAccessCodes() {
        assertThat(accessCodeStatus.getAccessCodes()).isNotNull();
    }

    @Test
    @DisplayName("test SetAccessCodeStatusName")
    void testSetAccessCodeStatusName() {
        accessCodeStatus.setAccessCodeStatusName("Pendiente");
        assertThat(accessCodeStatus.getAccessCodeStatusName()).isEqualTo("Pendiente");
    }
}
