package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.service.SlotReservationDetailsService;
import com.isoft.slot.managment.web.rest.errors.BadRequestAlertException;
import com.isoft.slot.managment.service.dto.SlotReservationDetailsDTO;

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
 * REST controller for managing {@link com.isoft.slot.managment.domain.SlotReservationDetails}.
 */
@RestController
@RequestMapping("/api")
public class SlotReservationDetailsResource {

    private final Logger log = LoggerFactory.getLogger(SlotReservationDetailsResource.class);

    private static final String ENTITY_NAME = "slotManagementSlotReservationDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SlotReservationDetailsService slotReservationDetailsService;

    public SlotReservationDetailsResource(SlotReservationDetailsService slotReservationDetailsService) {
        this.slotReservationDetailsService = slotReservationDetailsService;
    }

    /**
     * {@code POST  /slot-reservation-details} : Create a new slotReservationDetails.
     *
     * @param slotReservationDetailsDTO the slotReservationDetailsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new slotReservationDetailsDTO, or with status {@code 400 (Bad Request)} if the slotReservationDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/slot-reservation-details")
    public ResponseEntity<SlotReservationDetailsDTO> createSlotReservationDetails(@RequestBody SlotReservationDetailsDTO slotReservationDetailsDTO) throws URISyntaxException {
        log.debug("REST request to save SlotReservationDetails : {}", slotReservationDetailsDTO);
        if (slotReservationDetailsDTO.getId() != null) {
            throw new BadRequestAlertException("A new slotReservationDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlotReservationDetailsDTO result = slotReservationDetailsService.save(slotReservationDetailsDTO);
        return ResponseEntity.created(new URI("/api/slot-reservation-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /slot-reservation-details} : Updates an existing slotReservationDetails.
     *
     * @param slotReservationDetailsDTO the slotReservationDetailsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slotReservationDetailsDTO,
     * or with status {@code 400 (Bad Request)} if the slotReservationDetailsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the slotReservationDetailsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/slot-reservation-details")
    public ResponseEntity<SlotReservationDetailsDTO> updateSlotReservationDetails(@RequestBody SlotReservationDetailsDTO slotReservationDetailsDTO) throws URISyntaxException {
        log.debug("REST request to update SlotReservationDetails : {}", slotReservationDetailsDTO);
        if (slotReservationDetailsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SlotReservationDetailsDTO result = slotReservationDetailsService.save(slotReservationDetailsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slotReservationDetailsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /slot-reservation-details} : get all the slotReservationDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of slotReservationDetails in body.
     */
    @GetMapping("/slot-reservation-details")
    public List<SlotReservationDetailsDTO> getAllSlotReservationDetails() {
        log.debug("REST request to get all SlotReservationDetails");
        return slotReservationDetailsService.findAll();
    }

    /**
     * {@code GET  /slot-reservation-details/:id} : get the "id" slotReservationDetails.
     *
     * @param id the id of the slotReservationDetailsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the slotReservationDetailsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/slot-reservation-details/{id}")
    public ResponseEntity<SlotReservationDetailsDTO> getSlotReservationDetails(@PathVariable Long id) {
        log.debug("REST request to get SlotReservationDetails : {}", id);
        Optional<SlotReservationDetailsDTO> slotReservationDetailsDTO = slotReservationDetailsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(slotReservationDetailsDTO);
    }

    /**
     * {@code DELETE  /slot-reservation-details/:id} : delete the "id" slotReservationDetails.
     *
     * @param id the id of the slotReservationDetailsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/slot-reservation-details/{id}")
    public ResponseEntity<Void> deleteSlotReservationDetails(@PathVariable Long id) {
        log.debug("REST request to delete SlotReservationDetails : {}", id);
        slotReservationDetailsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
