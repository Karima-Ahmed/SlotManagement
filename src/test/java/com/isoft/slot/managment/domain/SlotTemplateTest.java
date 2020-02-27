package com.isoft.slot.managment.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotTemplateTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotTemplate.class);
        SlotTemplate slotTemplate1 = new SlotTemplate();
        slotTemplate1.setId(1L);
        SlotTemplate slotTemplate2 = new SlotTemplate();
        slotTemplate2.setId(slotTemplate1.getId());
        assertThat(slotTemplate1).isEqualTo(slotTemplate2);
        slotTemplate2.setId(2L);
        assertThat(slotTemplate1).isNotEqualTo(slotTemplate2);
        slotTemplate1.setId(null);
        assertThat(slotTemplate1).isNotEqualTo(slotTemplate2);
    }
}
