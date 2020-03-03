package com.isoft.slot.managment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotTemplateFacilitatorsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotTemplateFacilitatorsDTO.class);
        SlotTemplateFacilitatorsDTO slotTemplateFacilitatorsDTO1 = new SlotTemplateFacilitatorsDTO();
        slotTemplateFacilitatorsDTO1.setId(1L);
        SlotTemplateFacilitatorsDTO slotTemplateFacilitatorsDTO2 = new SlotTemplateFacilitatorsDTO();
        assertThat(slotTemplateFacilitatorsDTO1).isNotEqualTo(slotTemplateFacilitatorsDTO2);
        slotTemplateFacilitatorsDTO2.setId(slotTemplateFacilitatorsDTO1.getId());
        assertThat(slotTemplateFacilitatorsDTO1).isEqualTo(slotTemplateFacilitatorsDTO2);
        slotTemplateFacilitatorsDTO2.setId(2L);
        assertThat(slotTemplateFacilitatorsDTO1).isNotEqualTo(slotTemplateFacilitatorsDTO2);
        slotTemplateFacilitatorsDTO1.setId(null);
        assertThat(slotTemplateFacilitatorsDTO1).isNotEqualTo(slotTemplateFacilitatorsDTO2);
    }
}
