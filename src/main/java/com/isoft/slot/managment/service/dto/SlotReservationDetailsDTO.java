package com.isoft.slot.managment.service.dto;

import java.time.LocalDateTime;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * A DTO for the {@link com.isoft.slot.managment.domain.SlotReservationDetails} entity.
 */
public class SlotReservationDetailsDTO implements Serializable {

    private Long id;

    private BigDecimal applicantId;

    private BigDecimal status;

    private LocalDateTime timeFrom;

    private LocalDateTime timeTo;

    private BigDecimal requestNo;


    private Long slotId;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getApplicantId() {
        return applicantId;
    }

    public void setApplicantId(BigDecimal applicantId) {
        this.applicantId = applicantId;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public LocalDateTime getTimeFrom() {
        return timeFrom;
    }

    public void setTimeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalDateTime getTimeTo() {
        return timeTo;
    }

    public void setTimeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
    }

    public BigDecimal getRequestNo() {
        return requestNo;
    }

    public void setRequestNo(BigDecimal requestNo) {
        this.requestNo = requestNo;
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

        SlotReservationDetailsDTO slotReservationDetailsDTO = (SlotReservationDetailsDTO) o;
        if (slotReservationDetailsDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), slotReservationDetailsDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "SlotReservationDetailsDTO{" +
            "id=" + getId() +
            ", applicantId=" + getApplicantId() +
            ", status=" + getStatus() +
            ", timeFrom='" + getTimeFrom() + "'" +
            ", timeTo='" + getTimeTo() + "'" +
            ", requestNo=" + getRequestNo() +
            ", slotId=" + getSlotId() +
            "}";
    }
}
