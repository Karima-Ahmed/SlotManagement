package com.isoft.slot.managment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A SlotReservationDetails.
 */
@Entity
@Table(name = "slot_reservation_details")
public class SlotReservationDetails implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "applicant_id", precision = 21, scale = 2)
    private BigDecimal applicantId;

    @Column(name = "applicant_type", precision = 21, scale = 2)
    private BigDecimal applicantType;

    @Column(name = "status", precision = 21, scale = 2)
    private BigDecimal status;

    @ManyToOne
    @JsonIgnoreProperties("slotReservationDetails")
    private SlotInstance slotId;

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

    public BigDecimal getApplicantType() {
        return applicantType;
    }

    public SlotReservationDetails applicantType(BigDecimal applicantType) {
        this.applicantType = applicantType;
        return this;
    }

    public void setApplicantType(BigDecimal applicantType) {
        this.applicantType = applicantType;
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

    public SlotInstance getSlotId() {
        return slotId;
    }

    public SlotReservationDetails slotId(SlotInstance slotInstance) {
        this.slotId = slotInstance;
        return this;
    }

    public void setSlotId(SlotInstance slotInstance) {
        this.slotId = slotInstance;
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
            ", applicantType=" + getApplicantType() +
            ", status=" + getStatus() +
            "}";
    }
}
