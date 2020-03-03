package com.isoft.slot.managment.service.dto;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import com.isoft.slot.managment.web.rest.TestUtil;

public class SlotReservationDetailsDTOTest {

    @Test
    public void dtoEqualsVerifier() throws Exception {
        TestUtil.equalsVerifier(SlotReservationDetailsDTO.class);
        SlotReservationDetailsDTO slotReservationDetailsDTO1 = new SlotReservationDetailsDTO();
        slotReservationDetailsDTO1.setId(1L);
        SlotReservationDetailsDTO slotReservationDetailsDTO2 = new SlotReservationDetailsDTO();
        assertThat(slotReservationDetailsDTO1).isNotEqualTo(slotReservationDetailsDTO2);
        slotReservationDetailsDTO2.setId(slotReservationDetailsDTO1.getId());
        assertThat(slotReservationDetailsDTO1).isEqualTo(slotReservationDetailsDTO2);
        slotReservationDetailsDTO2.setId(2L);
        assertThat(slotReservationDetailsDTO1).isNotEqualTo(slotReservationDetailsDTO2);
        slotReservationDetailsDTO1.setId(null);
        assertThat(slotReservationDetailsDTO1).isNotEqualTo(slotReservationDetailsDTO2);
    }
}
