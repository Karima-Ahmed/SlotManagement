package com.isoft.slot.managment.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SlotTemplateMapperTest {

    private SlotTemplateMapper slotTemplateMapper;

    @BeforeEach
    public void setUp() {
        slotTemplateMapper = new SlotTemplateMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(slotTemplateMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(slotTemplateMapper.fromId(null)).isNull();
    }
}
