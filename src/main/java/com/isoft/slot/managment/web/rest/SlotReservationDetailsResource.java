package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.domain.SlotReservationDetails;
import com.isoft.slot.managment.repository.SlotReservationDetailsRepository;
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
 * REST controller for managing {@link com.isoft.slot.managment.domain.SlotReservationDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SlotReservationDetailsResource {

    private final Logger log = LoggerFactory.getLogger(SlotReservationDetailsResource.class);

    private static final String ENTITY_NAME = "slotManagementSlotReservationDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SlotReservationDetailsRepository slotReservationDetailsRepository;

    public SlotReservationDetailsResource(SlotReservationDetailsRepository slotReservationDetailsRepository) {
        this.slotReservationDetailsRepository = slotReservationDetailsRepository;
    }

    /**
     * {@code POST  /slot-reservation-details} : Create a new slotReservationDetails.
     *
     * @param slotReservationDetails the slotReservationDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new slotReservationDetails, or with status {@code 400 (Bad Request)} if the slotReservationDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/slot-reservation-details")
    public ResponseEntity<SlotReservationDetails> createSlotReservationDetails(@RequestBody SlotReservationDetails slotReservationDetails) throws URISyntaxException {
        log.debug("REST request to save SlotReservationDetails : {}", slotReservationDetails);
        if (slotReservationDetails.getId() != null) {
            throw new BadRequestAlertException("A new slotReservationDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlotReservationDetails result = slotReservationDetailsRepository.save(slotReservationDetails);
        return ResponseEntity.created(new URI("/api/slot-reservation-details/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /slot-reservation-details} : Updates an existing slotReservationDetails.
     *
     * @param slotReservationDetails the slotReservationDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slotReservationDetails,
     * or with status {@code 400 (Bad Request)} if the slotReservationDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the slotReservationDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/slot-reservation-details")
    public ResponseEntity<SlotReservationDetails> updateSlotReservationDetails(@RequestBody SlotReservationDetails slotReservationDetails) throws URISyntaxException {
        log.debug("REST request to update SlotReservationDetails : {}", slotReservationDetails);
        if (slotReservationDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SlotReservationDetails result = slotReservationDetailsRepository.save(slotReservationDetails);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slotReservationDetails.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /slot-reservation-details} : get all the slotReservationDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of slotReservationDetails in body.
     */
    @GetMapping("/slot-reservation-details")
    public List<SlotReservationDetails> getAllSlotReservationDetails() {
        log.debug("REST request to get all SlotReservationDetails");
        return slotReservationDetailsRepository.findAll();
    }

    /**
     * {@code GET  /slot-reservation-details/:id} : get the "id" slotReservationDetails.
     *
     * @param id the id of the slotReservationDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the slotReservationDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/slot-reservation-details/{id}")
    public ResponseEntity<SlotReservationDetails> getSlotReservationDetails(@PathVariable Long id) {
        log.debug("REST request to get SlotReservationDetails : {}", id);
        Optional<SlotReservationDetails> slotReservationDetails = slotReservationDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(slotReservationDetails);
    }

    /**
     * {@code DELETE  /slot-reservation-details/:id} : delete the "id" slotReservationDetails.
     *
     * @param id the id of the slotReservationDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/slot-reservation-details/{id}")
    public ResponseEntity<Void> deleteSlotReservationDetails(@PathVariable Long id) {
        log.debug("REST request to delete SlotReservationDetails : {}", id);
        slotReservationDetailsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
