package com.isoft.slot.managment.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SlotAssetsMapperTest {

    private SlotAssetsMapper slotAssetsMapper;

    @BeforeEach
    public void setUp() {
        slotAssetsMapper = new SlotAssetsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(slotAssetsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(slotAssetsMapper.fromId(null)).isNull();
    }
}
