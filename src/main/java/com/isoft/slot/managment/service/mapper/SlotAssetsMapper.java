package com.isoft.slot.managment.service.mapper;


import com.isoft.slot.managment.domain.*;
import com.isoft.slot.managment.service.dto.SlotAssetsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SlotAssets} and its DTO {@link SlotAssetsDTO}.
 */
@Mapper(componentModel = "spring", uses = {SlotInstanceMapper.class})
public interface SlotAssetsMapper extends EntityMapper<SlotAssetsDTO, SlotAssets> {

    @Mapping(source = "slot.id", target = "slotId")
    SlotAssetsDTO toDto(SlotAssets slotAssets);

    @Mapping(source = "slotId", target = "slot")
    SlotAssets toEntity(SlotAssetsDTO slotAssetsDTO);

    default SlotAssets fromId(Long id) {
        if (id == null) {
            return null;
        }
        SlotAssets slotAssets = new SlotAssets();
        slotAssets.setId(id);
        return slotAssets;
    }
}
