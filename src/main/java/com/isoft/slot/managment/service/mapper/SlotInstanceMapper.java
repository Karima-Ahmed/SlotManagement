package com.isoft.slot.managment.service.mapper;


import com.isoft.slot.managment.domain.*;
import com.isoft.slot.managment.service.dto.SlotInstanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SlotInstance} and its DTO {@link SlotInstanceDTO}.
 */
@Mapper(componentModel = "spring", uses = {SlotTemplateMapper.class})
public interface SlotInstanceMapper extends EntityMapper<SlotInstanceDTO, SlotInstance> {

    @Mapping(source = "slotTemplate.id", target = "slotTemplateId")
    SlotInstanceDTO toDto(SlotInstance slotInstance);

    @Mapping(target = "slotFacilitators", ignore = true)
    @Mapping(target = "removeSlotFacilitators", ignore = true)
    @Mapping(target = "slotAssets", ignore = true)
    @Mapping(target = "removeSlotAssets", ignore = true)
    @Mapping(target = "slotReservations", ignore = true)
    @Mapping(target = "removeSlotReservations", ignore = true)
    @Mapping(source = "slotTemplateId", target = "slotTemplate")
    SlotInstance toEntity(SlotInstanceDTO slotInstanceDTO);

    default SlotInstance fromId(Long id) {
        if (id == null) {
            return null;
        }
        SlotInstance slotInstance = new SlotInstance();
        slotInstance.setId(id);
        return slotInstance;
    }
}
