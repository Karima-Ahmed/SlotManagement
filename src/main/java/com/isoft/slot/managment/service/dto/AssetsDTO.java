package com.isoft.slot.managment.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.isoft.slot.managment.domain.Assets} entity.
 */
public class AssetsDTO implements Serializable {

    private Long id;

    private BigDecimal type;

    private BigDecimal centerId;

    private BigDecimal assetRefId;


    private Long slotAssetsId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getType() {
        return type;
    }

    public void setType(BigDecimal type) {
        this.type = type;
    }

    public BigDecimal getCenterId() {
        return centerId;
    }

    public void setCenterId(BigDecimal centerId) {
        this.centerId = centerId;
    }

    public BigDecimal getAssetRefId() {
        return assetRefId;
    }

    public void setAssetRefId(BigDecimal assetRefId) {
        this.assetRefId = assetRefId;
    }

    public Long getSlotAssetsId() {
        return slotAssetsId;
    }

    public void setSlotAssetsId(Long slotAssetsId) {
        this.slotAssetsId = slotAssetsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        AssetsDTO assetsDTO = (AssetsDTO) o;
        if (assetsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), assetsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "AssetsDTO{" +
            "id=" + getId() +
            ", type=" + getType() +
            ", centerId=" + getCenterId() +
            ", assetRefId=" + getAssetRefId() +
            ", slotAssetsId=" + getSlotAssetsId() +
            "}";
    }
}
