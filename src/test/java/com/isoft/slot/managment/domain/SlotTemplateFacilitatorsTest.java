package com.isoft.slot.managment.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotTemplateFacilitatorsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotTemplateFacilitators.class);
        SlotTemplateFacilitators slotTemplateFacilitators1 = new SlotTemplateFacilitators();
        slotTemplateFacilitators1.setId(1L);
        SlotTemplateFacilitators slotTemplateFacilitators2 = new SlotTemplateFacilitators();
        slotTemplateFacilitators2.setId(slotTemplateFacilitators1.getId());
        assertThat(slotTemplateFacilitators1).isEqualTo(slotTemplateFacilitators2);
        slotTemplateFacilitators2.setId(2L);
        assertThat(slotTemplateFacilitators1).isNotEqualTo(slotTemplateFacilitators2);
        slotTemplateFacilitators1.setId(null);
        assertThat(slotTemplateFacilitators1).isNotEqualTo(slotTemplateFacilitators2);
    }
}
