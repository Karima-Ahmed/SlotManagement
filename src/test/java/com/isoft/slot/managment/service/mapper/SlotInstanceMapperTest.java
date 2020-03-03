package com.isoft.slot.managment.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SlotInstanceMapperTest {

    private SlotInstanceMapper slotInstanceMapper;

    @BeforeEach
    public void setUp() {
        slotInstanceMapper = new SlotInstanceMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(slotInstanceMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(slotInstanceMapper.fromId(null)).isNull();
    }
}
