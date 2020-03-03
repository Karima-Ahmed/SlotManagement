package com.isoft.slot.managment.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.isoft.slot.managment.domain.SlotTemplateAssets} entity.
 */
public class SlotTemplateAssetsDTO implements Serializable {

    private Long id;

    private BigDecimal count;

    private BigDecimal assetType;


    private Long slotTempId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCount() {
        return count;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getAssetType() {
        return assetType;
    }

    public void setAssetType(BigDecimal assetType) {
        this.assetType = assetType;
    }

    public Long getSlotTempId() {
        return slotTempId;
    }

    public void setSlotTempId(Long slotTemplateId) {
        this.slotTempId = slotTemplateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SlotTemplateAssetsDTO slotTemplateAssetsDTO = (SlotTemplateAssetsDTO) o;
        if (slotTemplateAssetsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), slotTemplateAssetsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SlotTemplateAssetsDTO{" +
            "id=" + getId() +
            ", count=" + getCount() +
            ", assetType=" + getAssetType() +
            ", slotTempId=" + getSlotTempId() +
            "}";
    }
}
