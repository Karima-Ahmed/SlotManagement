package com.isoft.slot.managment.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotAssetsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotAssets.class);
        SlotAssets slotAssets1 = new SlotAssets();
        slotAssets1.setId(1L);
        SlotAssets slotAssets2 = new SlotAssets();
        slotAssets2.setId(slotAssets1.getId());
        assertThat(slotAssets1).isEqualTo(slotAssets2);
        slotAssets2.setId(2L);
        assertThat(slotAssets1).isNotEqualTo(slotAssets2);
        slotAssets1.setId(null);
        assertThat(slotAssets1).isNotEqualTo(slotAssets2);
    }
}
