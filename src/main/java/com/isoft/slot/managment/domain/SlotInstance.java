package com.isoft.slot.managment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * A SlotInstance.
 */
@Entity
@Table(name = "slot_instance")
public class SlotInstance extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slot_instance_s")
    @SequenceGenerator(name = "slot_instance_s")
    private Long id;

    @Column(name = "desc_ar")
    private String descAr;

    @Column(name = "desc_en")
    private String descEn;

    @Column(name = "time_frame", precision = 21, scale = 2)
    private BigDecimal timeFrame;

    @Column(name = "break_time", precision = 21, scale = 2)
    private BigDecimal breakTime;

    @Column(name = "time_from")
    private LocalDateTime timeFrom;

    @Column(name = "time_to")
    private LocalDateTime timeTo;

    @Column(name = "center_id", precision = 21, scale = 2)
    private BigDecimal centerId;

    @Column(name = "available_capacity", precision = 21, scale = 2)
    private BigDecimal availableCapacity;

    @ManyToOne
    @JsonIgnoreProperties("slotInstances")
    private SlotTemplate temp;

    @OneToMany(mappedBy = "slot")
    @JsonIgnoreProperties("slotAssets")
    private List<SlotAssets> slotAssets;

    @OneToMany(mappedBy = "slot")
    @JsonIgnoreProperties("slotFacilitators")
    private List<SlotFacilitators> slotFacilitators;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescAr() {
        return descAr;
    }

    public SlotInstance descAr(String descAr) {
        this.descAr = descAr;
        return this;
    }

    public void setDescAr(String descAr) {
        this.descAr = descAr;
    }

    public String getDescEn() {
        return descEn;
    }

    public SlotInstance descEn(String descEn) {
        this.descEn = descEn;
        return this;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public BigDecimal getTimeFrame() {
        return timeFrame;
    }

    public SlotInstance timeFrame(BigDecimal timeFrame) {
        this.timeFrame = timeFrame;
        return this;
    }

    public void setTimeFrame(BigDecimal timeFrame) {
        this.timeFrame = timeFrame;
    }

    public BigDecimal getBreakTime() {
        return breakTime;
    }

    public SlotInstance breakTime(BigDecimal breakTime) {
        this.breakTime = breakTime;
        return this;
    }

    public void setBreakTime(BigDecimal breakTime) {
        this.breakTime = breakTime;
    }

    public LocalDateTime getTimeFrom() {
        return timeFrom;
    }

    public SlotInstance timeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public void setTimeFrom(LocalDateTime timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalDateTime getTimeTo() {
        return timeTo;
    }

    public SlotInstance timeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public void setTimeTo(LocalDateTime timeTo) {
        this.timeTo = timeTo;
    }

    public BigDecimal getCenterId() {
        return centerId;
    }

    public SlotInstance centerId(BigDecimal centerId) {
        this.centerId = centerId;
        return this;
    }

    public void setCenterId(BigDecimal centerId) {
        this.centerId = centerId;
    }

    public BigDecimal getAvailableCapacity() {
        return availableCapacity;
    }

    public SlotInstance availableCapacity(BigDecimal availableCapacity) {
        this.availableCapacity = availableCapacity;
        return this;
    }

    public void setAvailableCapacity(BigDecimal availableCapacity) {
        this.availableCapacity = availableCapacity;
    }

    public SlotTemplate getTemp() {
        return temp;
    }

    public SlotInstance temp(SlotTemplate slotTemplate) {
        this.temp = slotTemplate;
        return this;
    }

    public void setTemp(SlotTemplate slotTemplate) {
        this.temp = slotTemplate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    public List<SlotAssets> getSlotAssets() {
        return slotAssets;
    }

    public void setSlotAssets(List<SlotAssets> slotAssets) {
        this.slotAssets = slotAssets;
    }

    public List<SlotFacilitators> getSlotFacilitators() {
        return slotFacilitators;
    }

    public void setSlotFacilitators(List<SlotFacilitators> slotFacilitators) {
        this.slotFacilitators = slotFacilitators;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SlotInstance)) {
            return false;
        }
        return id != null && id.equals(((SlotInstance) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SlotInstance{" +
            "id=" + getId() +
            ", descAr='" + getDescAr() + "'" +
            ", descEn='" + getDescEn() + "'" +
            ", timeFrame=" + getTimeFrame() +
            ", breakTime=" + getBreakTime() +
            ", timeFrom='" + getTimeFrom() + "'" +
            ", timeTo='" + getTimeTo() + "'" +
            ", centerId=" + getCenterId() +
            ", availableCapacity=" + getAvailableCapacity() +
            "}";
    }
}
