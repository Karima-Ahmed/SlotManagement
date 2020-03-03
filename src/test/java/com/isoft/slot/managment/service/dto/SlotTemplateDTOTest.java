package com.isoft.slot.managment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotTemplateDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotTemplateDTO.class);
        SlotTemplateDTO slotTemplateDTO1 = new SlotTemplateDTO();
        slotTemplateDTO1.setId(1L);
        SlotTemplateDTO slotTemplateDTO2 = new SlotTemplateDTO();
        assertThat(slotTemplateDTO1).isNotEqualTo(slotTemplateDTO2);
        slotTemplateDTO2.setId(slotTemplateDTO1.getId());
        assertThat(slotTemplateDTO1).isEqualTo(slotTemplateDTO2);
        slotTemplateDTO2.setId(2L);
        assertThat(slotTemplateDTO1).isNotEqualTo(slotTemplateDTO2);
        slotTemplateDTO1.setId(null);
        assertThat(slotTemplateDTO1).isNotEqualTo(slotTemplateDTO2);
    }
}
