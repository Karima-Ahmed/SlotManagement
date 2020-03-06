package com.isoft.slot.managment.service.mapper;


import com.isoft.slot.managment.domain.*;
import com.isoft.slot.managment.service.dto.SlotAssetsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link SlotAssets} and its DTO {@link SlotAssetsDTO}.
 */
@Mapper(componentModel = "spring", uses = {SlotInstanceMapper.class})
public interface SlotAssetsMapper extends EntityMapper<SlotAssetsDTO, SlotAssets> {

    @Mapping(source = "slotInstance.id", target = "slotInstanceId")
    SlotAssetsDTO toDto(SlotAssets slotAssets);

    @Mapping(target = "slotAssets", ignore = true)
    @Mapping(target = "removeSlotAssets", ignore = true)
    @Mapping(source = "slotInstanceId", target = "slotInstance")
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
