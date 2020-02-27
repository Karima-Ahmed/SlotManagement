package com.isoft.slot.managment.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A SlotTemplate.
 */
@Entity
@Table(name = "slot_template")
public class SlotTemplate implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "facilitator_no", precision = 21, scale = 2)
    private BigDecimal facilitatorNo;

    @Column(name = "facilitator_type", precision = 21, scale = 2)
    private BigDecimal facilitatorType;

    @Column(name = "capacity", precision = 21, scale = 2)
    private BigDecimal capacity;

    @Column(name = "time_frame", precision = 21, scale = 2)
    private BigDecimal timeFrame;

    @Column(name = "asset_type", precision = 21, scale = 2)
    private BigDecimal assetType;

    @Column(name = "break_time", precision = 21, scale = 2)
    private BigDecimal breakTime;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getFacilitatorNo() {
        return facilitatorNo;
    }

    public SlotTemplate facilitatorNo(BigDecimal facilitatorNo) {
        this.facilitatorNo = facilitatorNo;
        return this;
    }

    public void setFacilitatorNo(BigDecimal facilitatorNo) {
        this.facilitatorNo = facilitatorNo;
    }

    public BigDecimal getFacilitatorType() {
        return facilitatorType;
    }

    public SlotTemplate facilitatorType(BigDecimal facilitatorType) {
        this.facilitatorType = facilitatorType;
        return this;
    }

    public void setFacilitatorType(BigDecimal facilitatorType) {
        this.facilitatorType = facilitatorType;
    }

    public BigDecimal getCapacity() {
        return capacity;
    }

    public SlotTemplate capacity(BigDecimal capacity) {
        this.capacity = capacity;
        return this;
    }

    public void setCapacity(BigDecimal capacity) {
        this.capacity = capacity;
    }

    public BigDecimal getTimeFrame() {
        return timeFrame;
    }

    public SlotTemplate timeFrame(BigDecimal timeFrame) {
        this.timeFrame = timeFrame;
        return this;
    }

    public void setTimeFrame(BigDecimal timeFrame) {
        this.timeFrame = timeFrame;
    }

    public BigDecimal getAssetType() {
        return assetType;
    }

    public SlotTemplate assetType(BigDecimal assetType) {
        this.assetType = assetType;
        return this;
    }

    public void setAssetType(BigDecimal assetType) {
        this.assetType = assetType;
    }

    public BigDecimal getBreakTime() {
        return breakTime;
    }

    public SlotTemplate breakTime(BigDecimal breakTime) {
        this.breakTime = breakTime;
        return this;
    }

    public void setBreakTime(BigDecimal breakTime) {
        this.breakTime = breakTime;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SlotTemplate)) {
            return false;
        }
        return id != null && id.equals(((SlotTemplate) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SlotTemplate{" +
            "id=" + getId() +
            ", facilitatorNo=" + getFacilitatorNo() +
            ", facilitatorType=" + getFacilitatorType() +
            ", capacity=" + getCapacity() +
            ", timeFrame=" + getTimeFrame() +
            ", assetType=" + getAssetType() +
            ", breakTime=" + getBreakTime() +
            "}";
    }
}
