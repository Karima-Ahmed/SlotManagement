package com.isoft.slot.managment.service.mapper;


import com.isoft.slot.managment.domain.*;
import com.isoft.slot.managment.service.dto.SlotInstanceDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SlotInstance} and its DTO {@link SlotInstanceDTO}.
 */
@Mapper(componentModel = "spring", uses = {SlotTemplateMapper.class})
public interface SlotInstanceMapper extends EntityMapper<SlotInstanceDTO, SlotInstance> {

    @Mapping(source = "temp.id", target = "tempId")
    SlotInstanceDTO toDto(SlotInstance slotInstance);

    @Mapping(source = "tempId", target = "temp")
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
