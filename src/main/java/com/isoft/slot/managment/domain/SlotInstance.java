package com.isoft.slot.managment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

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

    @Column(name = "user_id", precision = 21, scale = 2)
    private BigDecimal userId;

    @Column(name = "time_from")
    private LocalDate timeFrom;

    @Column(name = "time_to")
    private LocalDate timeTo;

    @Column(name = "asset_id", precision = 21, scale = 2)
    private BigDecimal assetId;

    @ManyToOne
    @JsonIgnoreProperties("slotInstances")
    private SlotTemplate temp;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getUserId() {
        return userId;
    }

    public SlotInstance userId(BigDecimal userId) {
        this.userId = userId;
        return this;
    }

    public void setUserId(BigDecimal userId) {
        this.userId = userId;
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

    public BigDecimal getAssetId() {
        return assetId;
    }

    public SlotInstance assetId(BigDecimal assetId) {
        this.assetId = assetId;
        return this;
    }

    public void setAssetId(BigDecimal assetId) {
        this.assetId = assetId;
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
            ", userId=" + getUserId() +
            ", timeFrom='" + getTimeFrom() + "'" +
            ", timeTo='" + getTimeTo() + "'" +
            ", assetId=" + getAssetId() +
            "}";
    }
}
