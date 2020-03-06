package com.isoft.slot.managment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * A SlotReservationDetails.
 */
@Entity
@Table(name = "slot_reservation_details")
public class SlotReservationDetails extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum SlotStatus{
        AVAILABLE(new BigDecimal(1)),
        PARTIAL_RESERVED(new BigDecimal(2)),
        RESERVED(new BigDecimal(3)),
        BUSY(new BigDecimal(4));

        public static final String DOMAIN_CODE = "SlotStatus";

        private BigDecimal value;
        SlotStatus(BigDecimal value) {this.value = value;}
        public BigDecimal getValue() {return value;}
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slot_reservation_details_s")
    @SequenceGenerator(name = "slot_reservation_details_s")
    private Long id;

    @Column(name = "applicant_id", precision = 21, scale = 2)
    private BigDecimal applicantId;

    @Column(name = "status", precision = 21, scale = 2)
    private BigDecimal status;

    @Column(name = "time_from")
    private LocalDateTime timeFrom;

    @Column(name = "time_to")
    private LocalDateTime timeTo;

    @Column(name = "request_no", precision = 21, scale = 2)
    private BigDecimal requestNo;

    @ManyToOne
    @JsonIgnoreProperties("slotReservationDetails")
    private SlotInstance slot;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getApplicantId() {
        return applicantId;
    }

    public SlotReservationDetails applicantId(BigDecimal applicantId) {
        this.applicantId = applicantId;
        return this;
    }

    public void setApplicantId(BigDecimal applicantId) {
        this.applicantId = applicantId;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public SlotReservationDetails status(BigDecimal status) {
        this.status = status;
        return this;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public LocalDateTime getTimeFrom() {
        return timeFrom;
    }

    public SlotReservationDetails timeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public void setTimeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalDateTime getTimeTo() {
        return timeTo;
    }

    public SlotReservationDetails timeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public void setTimeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
    }

    public BigDecimal getRequestNo() {
        return requestNo;
    }

    public SlotReservationDetails requestNo(BigDecimal requestNo) {
        this.requestNo = requestNo;
        return this;
    }

    public void setRequestNo(BigDecimal requestNo) {
        this.requestNo = requestNo;
    }

    public SlotInstance getSlot() {
        return slot;
    }

    public SlotReservationDetails slot(SlotInstance slotInstance) {
        this.slot = slotInstance;
        return this;
    }

    public void setSlot(SlotInstance slotInstance) {
        this.slot = slotInstance;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SlotReservationDetails)) {
            return false;
        }
        return id != null && id.equals(((SlotReservationDetails) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SlotReservationDetails{" +
            "id=" + getId() +
            ", applicantId=" + getApplicantId() +
            ", status=" + getStatus() +
            ", timeFrom='" + getTimeFrom() + "'" +
            ", timeTo='" + getTimeTo() + "'" +
            ", requestNo=" + getRequestNo() +
            "}";
    }
}
