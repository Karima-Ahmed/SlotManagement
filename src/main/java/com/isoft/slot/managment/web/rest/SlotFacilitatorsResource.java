package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.service.SlotFacilitatorsService;
import com.isoft.slot.managment.web.rest.errors.BadRequestAlertException;
import com.isoft.slot.managment.service.dto.SlotFacilitatorsDTO;

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
 * REST controller for managing {@link com.isoft.slot.managment.domain.SlotFacilitators}.
 */
@RestController
@RequestMapping("/api")
public class SlotFacilitatorsResource {

    private final Logger log = LoggerFactory.getLogger(SlotFacilitatorsResource.class);

    private static final String ENTITY_NAME = "slotManagementSlotFacilitators";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SlotFacilitatorsService slotFacilitatorsService;

    public SlotFacilitatorsResource(SlotFacilitatorsService slotFacilitatorsService) {
        this.slotFacilitatorsService = slotFacilitatorsService;
    }

    /**
     * {@code POST  /slot-facilitators} : Create a new slotFacilitators.
     *
     * @param slotFacilitatorsDTO the slotFacilitatorsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new slotFacilitatorsDTO, or with status {@code 400 (Bad Request)} if the slotFacilitators has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/slot-facilitators")
    public ResponseEntity<SlotFacilitatorsDTO> createSlotFacilitators(@RequestBody SlotFacilitatorsDTO slotFacilitatorsDTO) throws URISyntaxException {
        log.debug("REST request to save SlotFacilitators : {}", slotFacilitatorsDTO);
        if (slotFacilitatorsDTO.getId() != null) {
            throw new BadRequestAlertException("A new slotFacilitators cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlotFacilitatorsDTO result = slotFacilitatorsService.save(slotFacilitatorsDTO);
        return ResponseEntity.created(new URI("/api/slot-facilitators/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /slot-facilitators} : Updates an existing slotFacilitators.
     *
     * @param slotFacilitatorsDTO the slotFacilitatorsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slotFacilitatorsDTO,
     * or with status {@code 400 (Bad Request)} if the slotFacilitatorsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the slotFacilitatorsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/slot-facilitators")
    public ResponseEntity<SlotFacilitatorsDTO> updateSlotFacilitators(@RequestBody SlotFacilitatorsDTO slotFacilitatorsDTO) throws URISyntaxException {
        log.debug("REST request to update SlotFacilitators : {}", slotFacilitatorsDTO);
        if (slotFacilitatorsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SlotFacilitatorsDTO result = slotFacilitatorsService.save(slotFacilitatorsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slotFacilitatorsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /slot-facilitators} : get all the slotFacilitators.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of slotFacilitators in body.
     */
    @GetMapping("/slot-facilitators")
    public List<SlotFacilitatorsDTO> getAllSlotFacilitators() {
        log.debug("REST request to get all SlotFacilitators");
        return slotFacilitatorsService.findAll();
    }

    /**
     * {@code GET  /slot-facilitators/:id} : get the "id" slotFacilitators.
     *
     * @param id the id of the slotFacilitatorsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the slotFacilitatorsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/slot-facilitators/{id}")
    public ResponseEntity<SlotFacilitatorsDTO> getSlotFacilitators(@PathVariable Long id) {
        log.debug("REST request to get SlotFacilitators : {}", id);
        Optional<SlotFacilitatorsDTO> slotFacilitatorsDTO = slotFacilitatorsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(slotFacilitatorsDTO);
    }

    /**
     * {@code DELETE  /slot-facilitators/:id} : delete the "id" slotFacilitators.
     *
     * @param id the id of the slotFacilitatorsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/slot-facilitators/{id}")
    public ResponseEntity<Void> deleteSlotFacilitators(@PathVariable Long id) {
        log.debug("REST request to delete SlotFacilitators : {}", id);
        slotFacilitatorsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
