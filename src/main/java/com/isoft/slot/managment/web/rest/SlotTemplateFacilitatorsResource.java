package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.domain.SlotTemplateFacilitators;
import com.isoft.slot.managment.repository.SlotTemplateFacilitatorsRepository;
import com.isoft.slot.managment.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.isoft.slot.managment.domain.SlotTemplateFacilitators}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SlotTemplateFacilitatorsResource {

    private final Logger log = LoggerFactory.getLogger(SlotTemplateFacilitatorsResource.class);

    private static final String ENTITY_NAME = "slotManagementSlotTemplateFacilitators";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SlotTemplateFacilitatorsRepository slotTemplateFacilitatorsRepository;

    public SlotTemplateFacilitatorsResource(SlotTemplateFacilitatorsRepository slotTemplateFacilitatorsRepository) {
        this.slotTemplateFacilitatorsRepository = slotTemplateFacilitatorsRepository;
    }

    /**
     * {@code POST  /slot-template-facilitators} : Create a new slotTemplateFacilitators.
     *
     * @param slotTemplateFacilitators the slotTemplateFacilitators to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new slotTemplateFacilitators, or with status {@code 400 (Bad Request)} if the slotTemplateFacilitators has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/slot-template-facilitators")
    public ResponseEntity<SlotTemplateFacilitators> createSlotTemplateFacilitators(@RequestBody SlotTemplateFacilitators slotTemplateFacilitators) throws URISyntaxException {
        log.debug("REST request to save SlotTemplateFacilitators : {}", slotTemplateFacilitators);
        if (slotTemplateFacilitators.getId() != null) {
            throw new BadRequestAlertException("A new slotTemplateFacilitators cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlotTemplateFacilitators result = slotTemplateFacilitatorsRepository.save(slotTemplateFacilitators);
        return ResponseEntity.created(new URI("/api/slot-template-facilitators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /slot-template-facilitators} : Updates an existing slotTemplateFacilitators.
     *
     * @param slotTemplateFacilitators the slotTemplateFacilitators to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slotTemplateFacilitators,
     * or with status {@code 400 (Bad Request)} if the slotTemplateFacilitators is not valid,
     * or with status {@code 500 (Internal Server Error)} if the slotTemplateFacilitators couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/slot-template-facilitators")
    public ResponseEntity<SlotTemplateFacilitators> updateSlotTemplateFacilitators(@RequestBody SlotTemplateFacilitators slotTemplateFacilitators) throws URISyntaxException {
        log.debug("REST request to update SlotTemplateFacilitators : {}", slotTemplateFacilitators);
        if (slotTemplateFacilitators.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SlotTemplateFacilitators result = slotTemplateFacilitatorsRepository.save(slotTemplateFacilitators);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slotTemplateFacilitators.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /slot-template-facilitators} : get all the slotTemplateFacilitators.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of slotTemplateFacilitators in body.
     */
    @GetMapping("/slot-template-facilitators")
    public List<SlotTemplateFacilitators> getAllSlotTemplateFacilitators() {
        log.debug("REST request to get all SlotTemplateFacilitators");
        return slotTemplateFacilitatorsRepository.findAll();
    }

    /**
     * {@code GET  /slot-template-facilitators/:id} : get the "id" slotTemplateFacilitators.
     *
     * @param id the id of the slotTemplateFacilitators to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the slotTemplateFacilitators, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/slot-template-facilitators/{id}")
    public ResponseEntity<SlotTemplateFacilitators> getSlotTemplateFacilitators(@PathVariable Long id) {
        log.debug("REST request to get SlotTemplateFacilitators : {}", id);
        Optional<SlotTemplateFacilitators> slotTemplateFacilitators = slotTemplateFacilitatorsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(slotTemplateFacilitators);
    }

    /**
     * {@code DELETE  /slot-template-facilitators/:id} : delete the "id" slotTemplateFacilitators.
     *
     * @param id the id of the slotTemplateFacilitators to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/slot-template-facilitators/{id}")
    public ResponseEntity<Void> deleteSlotTemplateFacilitators(@PathVariable Long id) {
        log.debug("REST request to delete SlotTemplateFacilitators : {}", id);
        slotTemplateFacilitatorsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
