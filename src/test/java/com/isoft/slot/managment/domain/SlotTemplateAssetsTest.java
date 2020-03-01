package com.isoft.slot.managment.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotTemplateAssetsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotTemplateAssets.class);
        SlotTemplateAssets slotTemplateAssets1 = new SlotTemplateAssets();
        slotTemplateAssets1.setId(1L);
        SlotTemplateAssets slotTemplateAssets2 = new SlotTemplateAssets();
        slotTemplateAssets2.setId(slotTemplateAssets1.getId());
        assertThat(slotTemplateAssets1).isEqualTo(slotTemplateAssets2);
        slotTemplateAssets2.setId(2L);
        assertThat(slotTemplateAssets1).isNotEqualTo(slotTemplateAssets2);
        slotTemplateAssets1.setId(null);
        assertThat(slotTemplateAssets1).isNotEqualTo(slotTemplateAssets2);
    }
}
