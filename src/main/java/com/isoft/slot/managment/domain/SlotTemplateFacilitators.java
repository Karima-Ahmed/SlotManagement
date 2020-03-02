package com.isoft.slot.managment.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A SlotTemplateFacilitators.
 */
@Entity
@Table(name = "slot_template_facilitators")
public class SlotTemplateFacilitators implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum FacilitatorType{
        LECTURER(new BigDecimal(1)),
        ASSISTANT(new BigDecimal(2)),
        TRAINER(new BigDecimal(3));

        public static final String DOMAIN_CODE = "FacilitatorType";

        private BigDecimal value;
        FacilitatorType(BigDecimal value) {this.value = value;}
        public BigDecimal getValue() {return value;}
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "count", precision = 21, scale = 2)
    private BigDecimal count;

    @Column(name = "type", precision = 21, scale = 2)
    private BigDecimal type;

    @ManyToOne
    @JsonIgnoreProperties("slotTemplateFacilitators")
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

    public SlotTemplateFacilitators count(BigDecimal count) {
        this.count = count;
        return this;
    }

    public void setCount(BigDecimal count) {
        this.count = count;
    }

    public BigDecimal getType() {
        return type;
    }

    public SlotTemplateFacilitators type(BigDecimal type) {
        this.type = type;
        return this;
    }

    public void setType(BigDecimal type) {
        this.type = type;
    }

    public SlotTemplate getSlotTemp() {
        return slotTemp;
    }

    public SlotTemplateFacilitators slotTemp(SlotTemplate slotTemplate) {
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
        if (!(o instanceof SlotTemplateFacilitators)) {
            return false;
        }
        return id != null && id.equals(((SlotTemplateFacilitators) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SlotTemplateFacilitators{" +
            "id=" + getId() +
            ", count=" + getCount() +
            ", type=" + getType() +
            "}";
    }
}
