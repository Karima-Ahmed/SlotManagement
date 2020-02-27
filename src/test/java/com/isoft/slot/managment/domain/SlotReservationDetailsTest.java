package com.isoft.slot.managment.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotReservationDetailsTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotReservationDetails.class);
        SlotReservationDetails slotReservationDetails1 = new SlotReservationDetails();
        slotReservationDetails1.setId(1L);
        SlotReservationDetails slotReservationDetails2 = new SlotReservationDetails();
        slotReservationDetails2.setId(slotReservationDetails1.getId());
        assertThat(slotReservationDetails1).isEqualTo(slotReservationDetails2);
        slotReservationDetails2.setId(2L);
        assertThat(slotReservationDetails1).isNotEqualTo(slotReservationDetails2);
        slotReservationDetails1.setId(null);
        assertThat(slotReservationDetails1).isNotEqualTo(slotReservationDetails2);
    }
}
