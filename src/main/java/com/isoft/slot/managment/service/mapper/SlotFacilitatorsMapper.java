package com.isoft.slot.managment.service.mapper;


import com.isoft.slot.managment.domain.*;
import com.isoft.slot.managment.service.dto.SlotFacilitatorsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SlotFacilitators} and its DTO {@link SlotFacilitatorsDTO}.
 */
@Mapper(componentModel = "spring", uses = {SlotInstanceMapper.class})
public interface SlotFacilitatorsMapper extends EntityMapper<SlotFacilitatorsDTO, SlotFacilitators> {

    @Mapping(source = "slot.id", target = "slotId")
    SlotFacilitatorsDTO toDto(SlotFacilitators slotFacilitators);

    @Mapping(source = "slotId", target = "slot")
    SlotFacilitators toEntity(SlotFacilitatorsDTO slotFacilitatorsDTO);

    default SlotFacilitators fromId(Long id) {
        if (id == null) {
            return null;
        }
        SlotFacilitators slotFacilitators = new SlotFacilitators();
        slotFacilitators.setId(id);
        return slotFacilitators;
    }
}
