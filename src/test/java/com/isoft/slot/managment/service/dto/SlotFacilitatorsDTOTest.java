package com.isoft.slot.managment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotFacilitatorsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotFacilitatorsDTO.class);
        SlotFacilitatorsDTO slotFacilitatorsDTO1 = new SlotFacilitatorsDTO();
        slotFacilitatorsDTO1.setId(1L);
        SlotFacilitatorsDTO slotFacilitatorsDTO2 = new SlotFacilitatorsDTO();
        assertThat(slotFacilitatorsDTO1).isNotEqualTo(slotFacilitatorsDTO2);
        slotFacilitatorsDTO2.setId(slotFacilitatorsDTO1.getId());
        assertThat(slotFacilitatorsDTO1).isEqualTo(slotFacilitatorsDTO2);
        slotFacilitatorsDTO2.setId(2L);
        assertThat(slotFacilitatorsDTO1).isNotEqualTo(slotFacilitatorsDTO2);
        slotFacilitatorsDTO1.setId(null);
        assertThat(slotFacilitatorsDTO1).isNotEqualTo(slotFacilitatorsDTO2);
    }
}
