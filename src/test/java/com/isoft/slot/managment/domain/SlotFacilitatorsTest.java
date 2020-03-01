package com.isoft.slot.managment.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotFacilitatorsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotFacilitators.class);
        SlotFacilitators slotFacilitators1 = new SlotFacilitators();
        slotFacilitators1.setId(1L);
        SlotFacilitators slotFacilitators2 = new SlotFacilitators();
        slotFacilitators2.setId(slotFacilitators1.getId());
        assertThat(slotFacilitators1).isEqualTo(slotFacilitators2);
        slotFacilitators2.setId(2L);
        assertThat(slotFacilitators1).isNotEqualTo(slotFacilitators2);
        slotFacilitators1.setId(null);
        assertThat(slotFacilitators1).isNotEqualTo(slotFacilitators2);
    }
}
