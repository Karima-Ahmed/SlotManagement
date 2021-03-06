package com.isoft.slot.managment.service.mapper;


import com.isoft.slot.managment.domain.*;
import com.isoft.slot.managment.service.dto.AssetsDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Assets} and its DTO {@link AssetsDTO}.
 */
@Mapper(componentModel = "spring", uses = {SlotAssetsMapper.class})
public interface AssetsMapper extends EntityMapper<AssetsDTO, Assets> {

    @Mapping(source = "slotAssets.id", target = "slotAssetsId")
    AssetsDTO toDto(Assets assets);

    @Mapping(source = "slotAssetsId", target = "slotAssets")
    Assets toEntity(AssetsDTO assetsDTO);

    default Assets fromId(Long id) {
        if (id == null) {
            return null;
        }
        Assets assets = new Assets();
        assets.setId(id);
        return assets;
    }
}
