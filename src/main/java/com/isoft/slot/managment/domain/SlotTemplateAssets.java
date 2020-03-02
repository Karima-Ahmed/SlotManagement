package com.isoft.slot.managment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A SlotTemplateAssets.
 */
@Entity
@Table(name = "slot_template_assets")
public class SlotTemplateAssets implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "count", precision = 21, scale = 2)
    private BigDecimal count;

    @Column(name = "asset_type", precision = 21, scale = 2)
    private BigDecimal assetType;

    @ManyToOne
    @JsonIgnoreProperties("slotTemplateAssets")
    private SlotTemplate slotTemp;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BigDecimal getCount() {
        return count;
    }

    public SlotTemplateAssets count(BigDecimal count) {
        this.count = count;
        return this;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getAssetType() {
        return assetType;
    }

    public SlotTemplateAssets assetType(BigDecimal assetType) {
        this.assetType = assetType;
        return this;
    }

    public void setAssetType(BigDecimal assetType) {
        this.assetType = assetType;
    }

    public SlotTemplate getSlotTemp() {
        return slotTemp;
    }

    public SlotTemplateAssets slotTemp(SlotTemplate slotTemplate) {
        this.slotTemp = slotTemplate;
        return this;
    }

    public void setSlotTemp(SlotTemplate slotTemplate) {
        this.slotTemp = slotTemplate;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SlotTemplateAssets)) {
            return false;
        }
        return id != null && id.equals(((SlotTemplateAssets) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SlotTemplateAssets{" +
            "id=" + getId() +
            ", count=" + getCount() +
            ", assetType=" + getAssetType() +
            "}";
    }
}
