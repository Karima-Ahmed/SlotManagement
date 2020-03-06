package com.isoft.slot.managment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A SlotInstance.
 */
@Entity
@Table(name = "slot_instance")
public class SlotInstance implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
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
    private LocalDate timeFrom;

    @Column(name = "time_to")
    private LocalDate timeTo;

    @Column(name = "center_id", precision = 21, scale = 2)
    private BigDecimal centerId;

    @Column(name = "available_capacity", precision = 21, scale = 2)
    private BigDecimal availableCapacity;

    @OneToMany(mappedBy = "slotInstance")
    private Set<SlotFacilitators> slotFacilitators = new HashSet<>();

    @OneToMany(mappedBy = "slotInstance")
    private Set<SlotAssets> slotAssets = new HashSet<>();

    @OneToMany(mappedBy = "slotInstance")
    private Set<SlotReservationDetails> slotReservations = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("slotInstances")
    private SlotTemplate slotTemplate;

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

    public LocalDate getTimeFrom() {
        return timeFrom;
    }

    public SlotInstance timeFrom(LocalDate timeFrom) {
        this.timeFrom = timeFrom;
        return this;
    }

    public void setTimeFrom(LocalDate timeFrom) {
        this.timeFrom = timeFrom;
    }

    public LocalDate getTimeTo() {
        return timeTo;
    }

    public SlotInstance timeTo(LocalDate timeTo) {
        this.timeTo = timeTo;
        return this;
    }

    public void setTimeTo(LocalDate timeTo) {
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

    public Set<SlotFacilitators> getSlotFacilitators() {
        return slotFacilitators;
    }

    public SlotInstance slotFacilitators(Set<SlotFacilitators> slotFacilitators) {
        this.slotFacilitators = slotFacilitators;
        return this;
    }

    public SlotInstance addSlotFacilitators(SlotFacilitators slotFacilitators) {
        this.slotFacilitators.add(slotFacilitators);
        slotFacilitators.setSlotInstance(this);
        return this;
    }

    public SlotInstance removeSlotFacilitators(SlotFacilitators slotFacilitators) {
        this.slotFacilitators.remove(slotFacilitators);
        slotFacilitators.setSlotInstance(null);
        return this;
    }

    public void setSlotFacilitators(Set<SlotFacilitators> slotFacilitators) {
        this.slotFacilitators = slotFacilitators;
    }

    public Set<SlotAssets> getSlotAssets() {
        return slotAssets;
    }

    public SlotInstance slotAssets(Set<SlotAssets> slotAssets) {
        this.slotAssets = slotAssets;
        return this;
    }

    public SlotInstance addSlotAssets(SlotAssets slotAssets) {
        this.slotAssets.add(slotAssets);
        slotAssets.setSlotInstance(this);
        return this;
    }

    public SlotInstance removeSlotAssets(SlotAssets slotAssets) {
        this.slotAssets.remove(slotAssets);
        slotAssets.setSlotInstance(null);
        return this;
    }

    public void setSlotAssets(Set<SlotAssets> slotAssets) {
        this.slotAssets = slotAssets;
    }

    public Set<SlotReservationDetails> getSlotReservations() {
        return slotReservations;
    }

    public SlotInstance slotReservations(Set<SlotReservationDetails> slotReservationDetails) {
        this.slotReservations = slotReservationDetails;
        return this;
    }

    public SlotInstance addSlotReservations(SlotReservationDetails slotReservationDetails) {
        this.slotReservations.add(slotReservationDetails);
        slotReservationDetails.setSlotInstance(this);
        return this;
    }

    public SlotInstance removeSlotReservations(SlotReservationDetails slotReservationDetails) {
        this.slotReservations.remove(slotReservationDetails);
        slotReservationDetails.setSlotInstance(null);
        return this;
    }

    public void setSlotReservations(Set<SlotReservationDetails> slotReservationDetails) {
        this.slotReservations = slotReservationDetails;
    }

    public SlotTemplate getSlotTemplate() {
        return slotTemplate;
    }

    public SlotInstance slotTemplate(SlotTemplate slotTemplate) {
        this.slotTemplate = slotTemplate;
        return this;
    }

    public void setSlotTemplate(SlotTemplate slotTemplate) {
        this.slotTemplate = slotTemplate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

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
