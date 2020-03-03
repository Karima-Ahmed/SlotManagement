package com.isoft.slot.managment.service.mapper;


import com.isoft.slot.managment.domain.*;
import com.isoft.slot.managment.service.dto.SlotReservationDetailsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SlotReservationDetails} and its DTO {@link SlotReservationDetailsDTO}.
 */
@Mapper(componentModel = "spring", uses = {SlotInstanceMapper.class})
public interface SlotReservationDetailsMapper extends EntityMapper<SlotReservationDetailsDTO, SlotReservationDetails> {

    @Mapping(source = "slot.id", target = "slotId")
    SlotReservationDetailsDTO toDto(SlotReservationDetails slotReservationDetails);

    @Mapping(source = "slotId", target = "slot")
    SlotReservationDetails toEntity(SlotReservationDetailsDTO slotReservationDetailsDTO);

    default SlotReservationDetails fromId(Long id) {
        if (id == null) {
            return null;
        }
        SlotReservationDetails slotReservationDetails = new SlotReservationDetails();
        slotReservationDetails.setId(id);
        return slotReservationDetails;
    }
}
