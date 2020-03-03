package com.isoft.slot.managment.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SlotReservationDetailsMapperTest {

    private SlotReservationDetailsMapper slotReservationDetailsMapper;

    @BeforeEach
    public void setUp() {
        slotReservationDetailsMapper = new SlotReservationDetailsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(slotReservationDetailsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(slotReservationDetailsMapper.fromId(null)).isNull();
    }
}
