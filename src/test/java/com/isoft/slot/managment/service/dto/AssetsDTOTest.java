package com.isoft.slot.managment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class AssetsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(AssetsDTO.class);
        AssetsDTO assetsDTO1 = new AssetsDTO();
        assetsDTO1.setId(1L);
        AssetsDTO assetsDTO2 = new AssetsDTO();
        assertThat(assetsDTO1).isNotEqualTo(assetsDTO2);
        assetsDTO2.setId(assetsDTO1.getId());
        assertThat(assetsDTO1).isEqualTo(assetsDTO2);
        assetsDTO2.setId(2L);
        assertThat(assetsDTO1).isNotEqualTo(assetsDTO2);
        assetsDTO1.setId(null);
        assertThat(assetsDTO1).isNotEqualTo(assetsDTO2);
    }
}
