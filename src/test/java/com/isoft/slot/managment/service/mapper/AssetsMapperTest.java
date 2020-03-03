package com.isoft.slot.managment.service.mapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class AssetsMapperTest {

    private AssetsMapper assetsMapper;

    @BeforeEach
    public void setUp() {
        assetsMapper = new AssetsMapperImpl();
    }

    @Test
    public void testEntityFromId() {
        Long id = 1L;
        assertThat(assetsMapper.fromId(id).getId()).isEqualTo(id);
        assertThat(assetsMapper.fromId(null)).isNull();
    }
}
