package com.isoft.slot.managment.service.dto;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the {@link com.isoft.slot.managment.domain.SlotAssets} entity.
 */
public class SlotAssetsDTO implements Serializable {

    private Long id;


    private Long slotId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getSlotId() {
        return slotId;
    }

    public void setSlotId(Long slotInstanceId) {
        this.slotId = slotInstanceId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SlotAssetsDTO slotAssetsDTO = (SlotAssetsDTO) o;
        if (slotAssetsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), slotAssetsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SlotAssetsDTO{" +
            "id=" + getId() +
            ", slotId=" + getSlotId() +
            "}";
    }
}
