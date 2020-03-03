package com.isoft.slot.managment.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.isoft.slot.managment.domain.SlotInstance} entity.
 */
public class SlotInstanceDTO implements Serializable {

    private Long id;

    private String descAr;

    private String descEn;

    private BigDecimal timeFrame;

    private BigDecimal breakTime;

    private LocalDate timeFrom;

    private LocalDate timeTo;

    private BigDecimal centerId;

    private BigDecimal availableCapacity;


    private Long tempId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescAr() {
        return descAr;
    }

    public void setDescAr(String descAr) {
        this.descAr = descAr;
    }

    public String getDescEn() {
        return descEn;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public BigDecimal getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(BigDecimal timeFrame) {
        this.timeFrame = timeFrame;
    }

    public BigDecimal getBreakTime() {
        return breakTime;
    }

    public void setBreakTime(BigDecimal breakTime) {
        this.breakTime = breakTime;
    }

    public LocalDate getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalDate timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalDate getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalDate timeTo) {
        this.timeTo = timeTo;
    }

    public BigDecimal getCenterId() {
        return centerId;
    }

    public void setCenterId(BigDecimal centerId) {
        this.centerId = centerId;
    }

    public BigDecimal getAvailableCapacity() {
        return availableCapacity;
    }

    public void setAvailableCapacity(BigDecimal availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public Long getTempId() {
        return tempId;
    }

    public void setTempId(Long slotTemplateId) {
        this.tempId = slotTemplateId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SlotInstanceDTO slotInstanceDTO = (SlotInstanceDTO) o;
        if (slotInstanceDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), slotInstanceDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SlotInstanceDTO{" +
            "id=" + getId() +
            ", descAr='" + getDescAr() + "'" +
            ", descEn='" + getDescEn() + "'" +
            ", timeFrame=" + getTimeFrame() +
            ", breakTime=" + getBreakTime() +
            ", timeFrom='" + getTimeFrom() + "'" +
            ", timeTo='" + getTimeTo() + "'" +
            ", centerId=" + getCenterId() +
            ", availableCapacity=" + getAvailableCapacity() +
            ", tempId=" + getTempId() +
            "}";
    }
}