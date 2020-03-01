package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.domain.SlotAssets;
import com.isoft.slot.managment.repository.SlotAssetsRepository;
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
 * REST controller for managing {@link com.isoft.slot.managment.domain.SlotAssets}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SlotAssetsResource {

    private final Logger log = LoggerFactory.getLogger(SlotAssetsResource.class);

    private static final String ENTITY_NAME = "slotManagementSlotAssets";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SlotAssetsRepository slotAssetsRepository;

    public SlotAssetsResource(SlotAssetsRepository slotAssetsRepository) {
        this.slotAssetsRepository = slotAssetsRepository;
    }

    /**
     * {@code POST  /slot-assets} : Create a new slotAssets.
     *
     * @param slotAssets the slotAssets to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new slotAssets, or with status {@code 400 (Bad Request)} if the slotAssets has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/slot-assets")
    public ResponseEntity<SlotAssets> createSlotAssets(@RequestBody SlotAssets slotAssets) throws URISyntaxException {
        log.debug("REST request to save SlotAssets : {}", slotAssets);
        if (slotAssets.getId() != null) {
            throw new BadRequestAlertException("A new slotAssets cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlotAssets result = slotAssetsRepository.save(slotAssets);
        return ResponseEntity.created(new URI("/api/slot-assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /slot-assets} : Updates an existing slotAssets.
     *
     * @param slotAssets the slotAssets to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slotAssets,
     * or with status {@code 400 (Bad Request)} if the slotAssets is not valid,
     * or with status {@code 500 (Internal Server Error)} if the slotAssets couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/slot-assets")
    public ResponseEntity<SlotAssets> updateSlotAssets(@RequestBody SlotAssets slotAssets) throws URISyntaxException {
        log.debug("REST request to update SlotAssets : {}", slotAssets);
        if (slotAssets.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SlotAssets result = slotAssetsRepository.save(slotAssets);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slotAssets.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /slot-assets} : get all the slotAssets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of slotAssets in body.
     */
    @GetMapping("/slot-assets")
    public List<SlotAssets> getAllSlotAssets() {
        log.debug("REST request to get all SlotAssets");
        return slotAssetsRepository.findAll();
    }

    /**
     * {@code GET  /slot-assets/:id} : get the "id" slotAssets.
     *
     * @param id the id of the slotAssets to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the slotAssets, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/slot-assets/{id}")
    public ResponseEntity<SlotAssets> getSlotAssets(@PathVariable Long id) {
        log.debug("REST request to get SlotAssets : {}", id);
        Optional<SlotAssets> slotAssets = slotAssetsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(slotAssets);
    }

    /**
     * {@code DELETE  /slot-assets/:id} : delete the "id" slotAssets.
     *
     * @param id the id of the slotAssets to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/slot-assets/{id}")
    public ResponseEntity<Void> deleteSlotAssets(@PathVariable Long id) {
        log.debug("REST request to delete SlotAssets : {}", id);
        slotAssetsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
