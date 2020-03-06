package com.isoft.slot.managment.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.isoft.slot.managment.domain.SlotTemplateFacilitators} entity.
 */
public class SlotTemplateFacilitatorsDTO implements Serializable {

    private Long id;

    private BigDecimal count;

    private BigDecimal facilitatorType;


    private Long slotTemplateId;

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

    public BigDecimal getFacilitatorType() {
        return facilitatorType;
    }

    public void setFacilitatorType(BigDecimal facilitatorType) {
        this.facilitatorType = facilitatorType;
    }

    public Long getSlotTemplateId() {
        return slotTemplateId;
    }

    public void setSlotTemplateId(Long slotTemplateId) {
        this.slotTemplateId = slotTemplateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SlotTemplateFacilitatorsDTO slotTemplateFacilitatorsDTO = (SlotTemplateFacilitatorsDTO) o;
        if (slotTemplateFacilitatorsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), slotTemplateFacilitatorsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SlotTemplateFacilitatorsDTO{" +
            "id=" + getId() +
            ", count=" + getCount() +
            ", facilitatorType=" + getFacilitatorType() +
            ", slotTemplateId=" + getSlotTemplateId() +
            "}";
    }
}
