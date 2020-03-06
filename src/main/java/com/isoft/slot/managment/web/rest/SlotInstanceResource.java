package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.service.SlotInstanceService;
import com.isoft.slot.managment.web.rest.errors.BadRequestAlertException;
import com.isoft.slot.managment.service.dto.SlotInstanceDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
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
public class SlotInstanceResource {

    private final Logger log = LoggerFactory.getLogger(SlotInstanceResource.class);

    private static final String ENTITY_NAME = "slotManagementSlotInstance";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SlotInstanceService slotInstanceService;

    public SlotInstanceResource(SlotInstanceService slotInstanceService) {
        this.slotInstanceService = slotInstanceService;
    }

    /**
     * {@code POST  /slot-instances} : Create a new slotInstance.
     *
     * @param slotInstanceDTO the slotInstanceDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new slotInstanceDTO, or with status {@code 400 (Bad Request)} if the slotInstance has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/slot-instances")
    public ResponseEntity<SlotInstanceDTO> createSlotInstance(@RequestBody SlotInstanceDTO slotInstanceDTO) throws URISyntaxException {
        log.debug("REST request to save SlotInstance : {}", slotInstanceDTO);
        if (slotInstanceDTO.getId() != null) {
            throw new BadRequestAlertException("A new slotInstance cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlotInstanceDTO result = slotInstanceService.save(slotInstanceDTO);
        return ResponseEntity.created(new URI("/api/slot-instances/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /slot-instances} : Updates an existing slotInstance.
     *
     * @param slotInstanceDTO the slotInstanceDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slotInstanceDTO,
     * or with status {@code 400 (Bad Request)} if the slotInstanceDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the slotInstanceDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/slot-instances")
    public ResponseEntity<SlotInstanceDTO> updateSlotInstance(@RequestBody SlotInstanceDTO slotInstanceDTO) throws URISyntaxException {
        log.debug("REST request to update SlotInstance : {}", slotInstanceDTO);
        if (slotInstanceDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SlotInstanceDTO result = slotInstanceService.save(slotInstanceDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slotInstanceDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /slot-instances} : get all the slotInstances.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of slotInstances in body.
     */
    @GetMapping("/slot-instances")
    public List<SlotInstanceDTO> getAllSlotInstances() {
        log.debug("REST request to get all SlotInstances");
        return slotInstanceService.findAll();
    }

    /**
     * {@code GET  /slot-instances/:id} : get the "id" slotInstance.
     *
     * @param id the id of the slotInstanceDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the slotInstanceDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/slot-instances/{id}")
    public ResponseEntity<SlotInstanceDTO> getSlotInstance(@PathVariable Long id) {
        log.debug("REST request to get SlotInstance : {}", id);
        Optional<SlotInstanceDTO> slotInstanceDTO = slotInstanceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(slotInstanceDTO);
    }

    /**
     * {@code DELETE  /slot-instances/:id} : delete the "id" slotInstance.
     *
     * @param id the id of the slotInstanceDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/slot-instances/{id}")
    public ResponseEntity<Void> deleteSlotInstance(@PathVariable Long id) {
        log.debug("REST request to delete SlotInstance : {}", id);
        slotInstanceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
