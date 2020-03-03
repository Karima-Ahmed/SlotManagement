package com.isoft.slot.managment.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.isoft.slot.managment.domain.SlotFacilitators} entity.
 */
public class SlotFacilitatorsDTO implements Serializable {

    private Long id;

    private BigDecimal userId;


    private Long slotId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
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

        SlotFacilitatorsDTO slotFacilitatorsDTO = (SlotFacilitatorsDTO) o;
        if (slotFacilitatorsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), slotFacilitatorsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SlotFacilitatorsDTO{" +
            "id=" + getId() +
            ", userId=" + getUserId() +
            ", slotId=" + getSlotId() +
            "}";
    }
}
