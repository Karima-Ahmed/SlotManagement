package com.isoft.slot.managment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotInstanceDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotInstanceDTO.class);
        SlotInstanceDTO slotInstanceDTO1 = new SlotInstanceDTO();
        slotInstanceDTO1.setId(1L);
        SlotInstanceDTO slotInstanceDTO2 = new SlotInstanceDTO();
        assertThat(slotInstanceDTO1).isNotEqualTo(slotInstanceDTO2);
        slotInstanceDTO2.setId(slotInstanceDTO1.getId());
        assertThat(slotInstanceDTO1).isEqualTo(slotInstanceDTO2);
        slotInstanceDTO2.setId(2L);
        assertThat(slotInstanceDTO1).isNotEqualTo(slotInstanceDTO2);
        slotInstanceDTO1.setId(null);
        assertThat(slotInstanceDTO1).isNotEqualTo(slotInstanceDTO2);
    }
}
