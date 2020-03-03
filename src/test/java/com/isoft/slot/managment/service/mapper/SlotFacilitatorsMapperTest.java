package com.isoft.slot.managment.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SlotFacilitatorsMapperTest {

    private SlotFacilitatorsMapper slotFacilitatorsMapper;

    @BeforeEach
    public void setUp() {
        slotFacilitatorsMapper = new SlotFacilitatorsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(slotFacilitatorsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(slotFacilitatorsMapper.fromId(null)).isNull();
    }
}
