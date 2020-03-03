package com.isoft.slot.managment.service.mapper;


import com.isoft.slot.managment.domain.*;
import com.isoft.slot.managment.service.dto.SlotTemplateDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SlotTemplate} and its DTO {@link SlotTemplateDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface SlotTemplateMapper extends EntityMapper<SlotTemplateDTO, SlotTemplate> {



    default SlotTemplate fromId(Long id) {
        if (id == null) {
            return null;
        }
        SlotTemplate slotTemplate = new SlotTemplate();
        slotTemplate.setId(id);
        return slotTemplate;
    }
}
