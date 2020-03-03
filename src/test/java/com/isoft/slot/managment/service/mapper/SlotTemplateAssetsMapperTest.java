package com.isoft.slot.managment.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class SlotTemplateAssetsMapperTest {

    private SlotTemplateAssetsMapper slotTemplateAssetsMapper;

    @BeforeEach
    public void setUp() {
        slotTemplateAssetsMapper = new SlotTemplateAssetsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(slotTemplateAssetsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(slotTemplateAssetsMapper.fromId(null)).isNull();
    }
}
