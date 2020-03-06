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
public class SlotTemplateFacilitators extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    public enum FacilitatorTypes{
        LECTURER(new BigDecimal(1)),
        ASSISTANT(new BigDecimal(2)),
        TRAINER(new BigDecimal(3));

        public static final String DOMAIN_CODE = "FacilitatorType";

        private BigDecimal value;
        FacilitatorTypes(BigDecimal value) {this.value = value;}
        public BigDecimal getValue() {return value;}
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "slot_template_facilitators_s")
    @SequenceGenerator(name = "slot_template_facilitators_S")
    private Long id;

    @Column(name = "count", precision = 21, scale = 2)
    private BigDecimal count;

    @Column(name = "facilitator_type", precision = 21, scale = 2)
    private BigDecimal facilitatorType;

    @ManyToOne
    @JsonIgnoreProperties("tempFacilitators")
    private SlotTemplate slotTemplate;

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

    public BigDecimal getFacilitatorType() {
        return facilitatorType;
    }

    public SlotTemplateFacilitators facilitatorType(BigDecimal facilitatorType) {
        this.facilitatorType = facilitatorType;
        return this;
    }

    public void setFacilitatorType(BigDecimal facilitatorType) {
        this.facilitatorType = facilitatorType;
    }

    public SlotTemplate getSlotTemplate() {
        return slotTemplate;
    }

    public SlotTemplateFacilitators slotTemplate(SlotTemplate slotTemplate) {
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
            ", facilitatorType=" + getFacilitatorType() +
            "}";
    }
}
