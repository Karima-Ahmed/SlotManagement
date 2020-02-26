package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.domain.SlotInstance;
import com.isoft.slot.managment.repository.SlotInstanceRepository;
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
 * REST controller for managing {@link com.isoft.slot.managment.domain.SlotInstance}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SlotInstanceResource {

    private final Logger log = LoggerFactory.getLogger(SlotInstanceResource.class);

    private static final String ENTITY_NAME = "slotManagementSlotInstance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SlotInstanceRepository slotInstanceRepository;

    public SlotInstanceResource(SlotInstanceRepository slotInstanceRepository) {
        this.slotInstanceRepository = slotInstanceRepository;
    }

    /**
     * {@code POST  /slot-instances} : Create a new slotInstance.
     *
     * @param slotInstance the slotInstance to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new slotInstance, or with status {@code 400 (Bad Request)} if the slotInstance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/slot-instances")
    public ResponseEntity<SlotInstance> createSlotInstance(@RequestBody SlotInstance slotInstance) throws URISyntaxException {
        log.debug("REST request to save SlotInstance : {}", slotInstance);
        if (slotInstance.getId() != null) {
            throw new BadRequestAlertException("A new slotInstance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlotInstance result = slotInstanceRepository.save(slotInstance);
        return ResponseEntity.created(new URI("/api/slot-instances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /slot-instances} : Updates an existing slotInstance.
     *
     * @param slotInstance the slotInstance to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slotInstance,
     * or with status {@code 400 (Bad Request)} if the slotInstance is not valid,
     * or with status {@code 500 (Internal Server Error)} if the slotInstance couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/slot-instances")
    public ResponseEntity<SlotInstance> updateSlotInstance(@RequestBody SlotInstance slotInstance) throws URISyntaxException {
        log.debug("REST request to update SlotInstance : {}", slotInstance);
        if (slotInstance.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SlotInstance result = slotInstanceRepository.save(slotInstance);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slotInstance.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /slot-instances} : get all the slotInstances.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of slotInstances in body.
     */
    @GetMapping("/slot-instances")
    public List<SlotInstance> getAllSlotInstances() {
        log.debug("REST request to get all SlotInstances");
        return slotInstanceRepository.findAll();
    }

    /**
     * {@code GET  /slot-instances/:id} : get the "id" slotInstance.
     *
     * @param id the id of the slotInstance to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the slotInstance, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/slot-instances/{id}")
    public ResponseEntity<SlotInstance> getSlotInstance(@PathVariable Long id) {
        log.debug("REST request to get SlotInstance : {}", id);
        Optional<SlotInstance> slotInstance = slotInstanceRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(slotInstance);
    }

    /**
     * {@code DELETE  /slot-instances/:id} : delete the "id" slotInstance.
     *
     * @param id the id of the slotInstance to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/slot-instances/{id}")
    public ResponseEntity<Void> deleteSlotInstance(@PathVariable Long id) {
        log.debug("REST request to delete SlotInstance : {}", id);
        slotInstanceRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
