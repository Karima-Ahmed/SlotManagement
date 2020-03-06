package com.isoft.slot.managment.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.isoft.slot.managment.domain.SlotTemplate} entity.
 */
public class SlotTemplateDTO implements Serializable {

    private Long id;

    private BigDecimal capacity;

    private BigDecimal timeFrame;

    private BigDecimal breakTime;

    private LocalDate dayStartTime;

    private LocalDate dayEndTime;

    private String descAr;

    private String descEn;

    private BigDecimal centerId;

    private BigDecimal status;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
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

    public LocalDate getDayStartTime() {
        return dayStartTime;
    }

    public void setDayStartTime(LocalDate dayStartTime) {
        this.dayStartTime = dayStartTime;
    }

    public LocalDate getDayEndTime() {
        return dayEndTime;
    }

    public void setDayEndTime(LocalDate dayEndTime) {
        this.dayEndTime = dayEndTime;
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

    public BigDecimal getCenterId() {
        return centerId;
    }

    public void setCenterId(BigDecimal centerId) {
        this.centerId = centerId;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        SlotTemplateDTO slotTemplateDTO = (SlotTemplateDTO) o;
        if (slotTemplateDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), slotTemplateDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SlotTemplateDTO{" +
            "id=" + getId() +
            ", capacity=" + getCapacity() +
            ", timeFrame=" + getTimeFrame() +
            ", breakTime=" + getBreakTime() +
            ", dayStartTime='" + getDayStartTime() + "'" +
            ", dayEndTime='" + getDayEndTime() + "'" +
            ", descAr='" + getDescAr() + "'" +
            ", descEn='" + getDescEn() + "'" +
            ", centerId=" + getCenterId() +
            ", status=" + getStatus() +
            "}";
    }
}
