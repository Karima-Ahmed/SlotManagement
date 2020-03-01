package com.isoft.slot.managment.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class AssetsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Assets.class);
        Assets assets1 = new Assets();
        assets1.setId(1L);
        Assets assets2 = new Assets();
        assets2.setId(assets1.getId());
        assertThat(assets1).isEqualTo(assets2);
        assets2.setId(2L);
        assertThat(assets1).isNotEqualTo(assets2);
        assets1.setId(null);
        assertThat(assets1).isNotEqualTo(assets2);
    }
}
