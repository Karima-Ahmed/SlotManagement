package com.isoft.slot.managment.domain;


import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.Set;

/**
 * A SlotTemplate.
 */
@Entity
@Table(name = "slot_template")
public class SlotTemplate extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum slotTempStatus{
        Active(new BigDecimal(1)),
        INACTIVE(new BigDecimal(2));

        public static final String DOMAIN_CODE = "slotTempStatus";

        private BigDecimal value;
        slotTempStatus(BigDecimal value) {this.value = value;}
        public BigDecimal getValue() {return value;}
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slot_template_s")
    @SequenceGenerator(name = "slot_template_s")
    private Long id;

    @Column(name = "capacity", precision = 21, scale = 2)
    private BigDecimal capacity;

    @Column(name = "time_frame", precision = 21, scale = 2)
    private Duration timeFrame;

    @Column(name = "break_time", precision = 21, scale = 2)
    private Duration breakTime;

    @Column(name = "day_start_time")
    private LocalTime dayStartTime;

    @Column(name = "day_end_time")
    private LocalTime dayEndTime;

    @Column(name = "desc_ar")
    private String descAr;

    @Column(name = "desc_en")
    private String descEn;

    @Column(name = "center_id", precision = 21, scale = 2)
    private BigDecimal centerId;

    @Column(name = "status", precision = 21, scale = 2)
    private BigDecimal status;

    @OneToMany(mappedBy = "slotTemplate")
    private Set<SlotTemplateAssets> tempAssets = new HashSet<>();

    @OneToMany(mappedBy = "slotTemplate")
    private Set<SlotTemplateFacilitators> tempFacilitators = new HashSet<>();

    @OneToMany(mappedBy = "slotTemplate")
    private Set<SlotInstance> slotInstances = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Duration getTimeFrame() {
        return timeFrame;
    }

    public SlotTemplate timeFrame(Duration timeFrame) {
        this.timeFrame = timeFrame;
        return this;
    }

    public void setTimeFrame(Duration timeFrame) {
        this.timeFrame = timeFrame;
    }

    public Duration getBreakTime() {
        return breakTime;
    }

    public SlotTemplate breakTime(Duration breakTime) {
        this.breakTime = breakTime;
        return this;
    }

    public void setBreakTime(Duration breakTime) {
        this.breakTime = breakTime;
    }

    public LocalTime getDayStartTime() {
        return dayStartTime;
    }

    public SlotTemplate dayStartTime(LocalTime dayStartTime) {
        this.dayStartTime = dayStartTime;
        return this;
    }

    public void setDayStartTime(LocalTime dayStartTime) {
        this.dayStartTime = dayStartTime;
    }

    public LocalTime getDayEndTime() {
        return dayEndTime;
    }

    public SlotTemplate dayEndTime(LocalTime dayEndTime) {
        this.dayEndTime = dayEndTime;
        return this;
    }

    public void setDayEndTime(LocalTime dayEndTime) {
        this.dayEndTime = dayEndTime;
    }

    public String getDescAr() {
        return descAr;
    }

    public SlotTemplate descAr(String descAr) {
        this.descAr = descAr;
        return this;
    }

    public void setDescAr(String descAr) {
        this.descAr = descAr;
    }

    public String getDescEn() {
        return descEn;
    }

    public SlotTemplate descEn(String descEn) {
        this.descEn = descEn;
        return this;
    }

    public void setDescEn(String descEn) {
        this.descEn = descEn;
    }

    public BigDecimal getCenterId() {
        return centerId;
    }

    public SlotTemplate centerId(BigDecimal centerId) {
        this.centerId = centerId;
        return this;
    }

    public void setCenterId(BigDecimal centerId) {
        this.centerId = centerId;
    }

    public BigDecimal getStatus() {
        return status;
    }

    public SlotTemplate status(BigDecimal status) {
        this.status = status;
        return this;
    }

    public void setStatus(BigDecimal status) {
        this.status = status;
    }

    public Set<SlotTemplateAssets> getTempAssets() {
        return tempAssets;
    }

    public SlotTemplate tempAssets(Set<SlotTemplateAssets> slotTemplateAssets) {
        this.tempAssets = slotTemplateAssets;
        return this;
    }

    public SlotTemplate addTempAssets(SlotTemplateAssets slotTemplateAssets) {
        this.tempAssets.add(slotTemplateAssets);
        slotTemplateAssets.setSlotTemplate(this);
        return this;
    }

    public SlotTemplate removeTempAssets(SlotTemplateAssets slotTemplateAssets) {
        this.tempAssets.remove(slotTemplateAssets);
        slotTemplateAssets.setSlotTemplate(null);
        return this;
    }

    public void setTempAssets(Set<SlotTemplateAssets> slotTemplateAssets) {
        this.tempAssets = slotTemplateAssets;
    }

    public Set<SlotTemplateFacilitators> getTempFacilitators() {
        return tempFacilitators;
    }

    public SlotTemplate tempFacilitators(Set<SlotTemplateFacilitators> slotTemplateFacilitators) {
        this.tempFacilitators = slotTemplateFacilitators;
        return this;
    }

    public SlotTemplate addTempFacilitators(SlotTemplateFacilitators slotTemplateFacilitators) {
        this.tempFacilitators.add(slotTemplateFacilitators);
        slotTemplateFacilitators.setSlotTemplate(this);
        return this;
    }

    public SlotTemplate removeTempFacilitators(SlotTemplateFacilitators slotTemplateFacilitators) {
        this.tempFacilitators.remove(slotTemplateFacilitators);
        slotTemplateFacilitators.setSlotTemplate(null);
        return this;
    }

    public void setTempFacilitators(Set<SlotTemplateFacilitators> slotTemplateFacilitators) {
        this.tempFacilitators = slotTemplateFacilitators;
    }

    public Set<SlotInstance> getSlotInstances() {
        return slotInstances;
    }

    public SlotTemplate slotInstances(Set<SlotInstance> slotInstances) {
        this.slotInstances = slotInstances;
        return this;
    }

    public SlotTemplate addSlotInstances(SlotInstance slotInstance) {
        this.slotInstances.add(slotInstance);
        slotInstance.setSlotTemplate(this);
        return this;
    }

    public SlotTemplate removeSlotInstances(SlotInstance slotInstance) {
        this.slotInstances.remove(slotInstance);
        slotInstance.setSlotTemplate(null);
        return this;
    }

    public void setSlotInstances(Set<SlotInstance> slotInstances) {
        this.slotInstances = slotInstances;
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
