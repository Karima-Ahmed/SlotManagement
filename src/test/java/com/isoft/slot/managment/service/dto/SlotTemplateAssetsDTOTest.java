package com.isoft.slot.managment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotTemplateAssetsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotTemplateAssetsDTO.class);
        SlotTemplateAssetsDTO slotTemplateAssetsDTO1 = new SlotTemplateAssetsDTO();
        slotTemplateAssetsDTO1.setId(1L);
        SlotTemplateAssetsDTO slotTemplateAssetsDTO2 = new SlotTemplateAssetsDTO();
        assertThat(slotTemplateAssetsDTO1).isNotEqualTo(slotTemplateAssetsDTO2);
        slotTemplateAssetsDTO2.setId(slotTemplateAssetsDTO1.getId());
        assertThat(slotTemplateAssetsDTO1).isEqualTo(slotTemplateAssetsDTO2);
        slotTemplateAssetsDTO2.setId(2L);
        assertThat(slotTemplateAssetsDTO1).isNotEqualTo(slotTemplateAssetsDTO2);
        slotTemplateAssetsDTO1.setId(null);
        assertThat(slotTemplateAssetsDTO1).isNotEqualTo(slotTemplateAssetsDTO2);
    }
}
