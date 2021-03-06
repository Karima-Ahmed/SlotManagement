package com.isoft.slot.managment.service.mapper;


import com.isoft.slot.managment.domain.*;
import com.isoft.slot.managment.service.dto.SlotTemplateFacilitatorsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SlotTemplateFacilitators} and its DTO {@link SlotTemplateFacilitatorsDTO}.
 */
@Mapper(componentModel = "spring", uses = {SlotTemplateMapper.class})
public interface SlotTemplateFacilitatorsMapper extends EntityMapper<SlotTemplateFacilitatorsDTO, SlotTemplateFacilitators> {

    @Mapping(source = "slotTemplate.id", target = "slotTemplateId")
    SlotTemplateFacilitatorsDTO toDto(SlotTemplateFacilitators slotTemplateFacilitators);

    @Mapping(source = "slotTemplateId", target = "slotTemplate")
    SlotTemplateFacilitators toEntity(SlotTemplateFacilitatorsDTO slotTemplateFacilitatorsDTO);

    default SlotTemplateFacilitators fromId(Long id) {
        if (id == null) {
            return null;
        }
        SlotTemplateFacilitators slotTemplateFacilitators = new SlotTemplateFacilitators();
        slotTemplateFacilitators.setId(id);
        return slotTemplateFacilitators;
    }
}
