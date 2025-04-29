package com.f5.buzon_inteligente_BE.accesscode.DTO;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AccessCodeRequestDTOTest {

    @Test
    @DisplayName("Should create AccessCodeRequestDTO using all-args constructor")
    void testAllArgsConstructor() {
        Long profileId = 1L;
        String accessCodeName = "Test Access Code";

        AccessCodeRequestDTO dto = new AccessCodeRequestDTO(profileId, accessCodeName);

        assertThat(dto.getProfileId()).isEqualTo(profileId);
        assertThat(dto.getAccessCodeName()).isEqualTo(accessCodeName);
    }

    @Test
    @DisplayName("Should set and get fields using setters and getters")
    void testSettersAndGetters() {
        AccessCodeRequestDTO dto = new AccessCodeRequestDTO();

        dto.setProfileId(2L);
        dto.setAccessCodeName("Another Access Code");

        assertThat(dto.getProfileId()).isEqualTo(2L);
        assertThat(dto.getAccessCodeName()).isEqualTo("Another Access Code");
    }

}

