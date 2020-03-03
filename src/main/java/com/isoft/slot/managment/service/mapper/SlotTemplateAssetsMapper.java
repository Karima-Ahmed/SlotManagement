package com.isoft.slot.managment.service.mapper;


import com.isoft.slot.managment.domain.*;
import com.isoft.slot.managment.service.dto.SlotTemplateAssetsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SlotTemplateAssets} and its DTO {@link SlotTemplateAssetsDTO}.
 */
@Mapper(componentModel = "spring", uses = {SlotTemplateMapper.class})
public interface SlotTemplateAssetsMapper extends EntityMapper<SlotTemplateAssetsDTO, SlotTemplateAssets> {

    @Mapping(source = "slotTemp.id", target = "slotTempId")
    SlotTemplateAssetsDTO toDto(SlotTemplateAssets slotTemplateAssets);

    @Mapping(source = "slotTempId", target = "slotTemp")
    SlotTemplateAssets toEntity(SlotTemplateAssetsDTO slotTemplateAssetsDTO);

    default SlotTemplateAssets fromId(Long id) {
        if (id == null) {
            return null;
        }
        SlotTemplateAssets slotTemplateAssets = new SlotTemplateAssets();
        slotTemplateAssets.setId(id);
        return slotTemplateAssets;
    }
}
