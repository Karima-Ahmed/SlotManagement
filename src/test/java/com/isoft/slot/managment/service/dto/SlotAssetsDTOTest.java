package com.isoft.slot.managment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotAssetsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotAssetsDTO.class);
        SlotAssetsDTO slotAssetsDTO1 = new SlotAssetsDTO();
        slotAssetsDTO1.setId(1L);
        SlotAssetsDTO slotAssetsDTO2 = new SlotAssetsDTO();
        assertThat(slotAssetsDTO1).isNotEqualTo(slotAssetsDTO2);
        slotAssetsDTO2.setId(slotAssetsDTO1.getId());
        assertThat(slotAssetsDTO1).isEqualTo(slotAssetsDTO2);
        slotAssetsDTO2.setId(2L);
        assertThat(slotAssetsDTO1).isNotEqualTo(slotAssetsDTO2);
        slotAssetsDTO1.setId(null);
        assertThat(slotAssetsDTO1).isNotEqualTo(slotAssetsDTO2);
    }
}
