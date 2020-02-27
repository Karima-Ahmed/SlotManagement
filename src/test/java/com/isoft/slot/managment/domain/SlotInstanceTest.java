package com.isoft.slot.managment.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotInstanceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotInstance.class);
        SlotInstance slotInstance1 = new SlotInstance();
        slotInstance1.setId(1L);
        SlotInstance slotInstance2 = new SlotInstance();
        slotInstance2.setId(slotInstance1.getId());
        assertThat(slotInstance1).isEqualTo(slotInstance2);
        slotInstance2.setId(2L);
        assertThat(slotInstance1).isNotEqualTo(slotInstance2);
        slotInstance1.setId(null);
        assertThat(slotInstance1).isNotEqualTo(slotInstance2);
    }
}
