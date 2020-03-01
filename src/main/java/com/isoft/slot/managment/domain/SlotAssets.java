package com.isoft.slot.managment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A SlotAssets.
 */
@Entity
@Table(name = "slot_assets")
public class SlotAssets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne
    @JsonIgnoreProperties("slotAssets")
    private SlotInstance slot;

    @ManyToOne
    @JsonIgnoreProperties("slotAssets")
    private Assets asset;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SlotInstance getSlot() {
        return slot;
    }

    public SlotAssets slot(SlotInstance slotInstance) {
        this.slot = slotInstance;
        return this;
    }

    public void setSlot(SlotInstance slotInstance) {
        this.slot = slotInstance;
    }

    public Assets getAsset() {
        return asset;
    }

    public SlotAssets asset(Assets assets) {
        this.asset = assets;
        return this;
    }

    public void setAsset(Assets assets) {
        this.asset = assets;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SlotAssets)) {
            return false;
        }
        return id != null && id.equals(((SlotAssets) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SlotAssets{" +
            "id=" + getId() +
            "}";
    }
}
