package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.domain.SlotTemplateAssets;
import com.isoft.slot.managment.repository.SlotTemplateAssetsRepository;
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
 * REST controller for managing {@link com.isoft.slot.managment.domain.SlotTemplateAssets}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class SlotTemplateAssetsResource {

    private final Logger log = LoggerFactory.getLogger(SlotTemplateAssetsResource.class);

    private static final String ENTITY_NAME = "slotManagementSlotTemplateAssets";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SlotTemplateAssetsRepository slotTemplateAssetsRepository;

    public SlotTemplateAssetsResource(SlotTemplateAssetsRepository slotTemplateAssetsRepository) {
        this.slotTemplateAssetsRepository = slotTemplateAssetsRepository;
    }

    /**
     * {@code POST  /slot-template-assets} : Create a new slotTemplateAssets.
     *
     * @param slotTemplateAssets the slotTemplateAssets to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new slotTemplateAssets, or with status {@code 400 (Bad Request)} if the slotTemplateAssets has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/slot-template-assets")
    public ResponseEntity<SlotTemplateAssets> createSlotTemplateAssets(@RequestBody SlotTemplateAssets slotTemplateAssets) throws URISyntaxException {
        log.debug("REST request to save SlotTemplateAssets : {}", slotTemplateAssets);
        if (slotTemplateAssets.getId() != null) {
            throw new BadRequestAlertException("A new slotTemplateAssets cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlotTemplateAssets result = slotTemplateAssetsRepository.save(slotTemplateAssets);
        return ResponseEntity.created(new URI("/api/slot-template-assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /slot-template-assets} : Updates an existing slotTemplateAssets.
     *
     * @param slotTemplateAssets the slotTemplateAssets to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slotTemplateAssets,
     * or with status {@code 400 (Bad Request)} if the slotTemplateAssets is not valid,
     * or with status {@code 500 (Internal Server Error)} if the slotTemplateAssets couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/slot-template-assets")
    public ResponseEntity<SlotTemplateAssets> updateSlotTemplateAssets(@RequestBody SlotTemplateAssets slotTemplateAssets) throws URISyntaxException {
        log.debug("REST request to update SlotTemplateAssets : {}", slotTemplateAssets);
        if (slotTemplateAssets.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SlotTemplateAssets result = slotTemplateAssetsRepository.save(slotTemplateAssets);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slotTemplateAssets.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /slot-template-assets} : get all the slotTemplateAssets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of slotTemplateAssets in body.
     */
    @GetMapping("/slot-template-assets")
    public List<SlotTemplateAssets> getAllSlotTemplateAssets() {
        log.debug("REST request to get all SlotTemplateAssets");
        return slotTemplateAssetsRepository.findAll();
    }

    /**
     * {@code GET  /slot-template-assets/:id} : get the "id" slotTemplateAssets.
     *
     * @param id the id of the slotTemplateAssets to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the slotTemplateAssets, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/slot-template-assets/{id}")
    public ResponseEntity<SlotTemplateAssets> getSlotTemplateAssets(@PathVariable Long id) {
        log.debug("REST request to get SlotTemplateAssets : {}", id);
        Optional<SlotTemplateAssets> slotTemplateAssets = slotTemplateAssetsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(slotTemplateAssets);
    }

    /**
     * {@code DELETE  /slot-template-assets/:id} : delete the "id" slotTemplateAssets.
     *
     * @param id the id of the slotTemplateAssets to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/slot-template-assets/{id}")
    public ResponseEntity<Void> deleteSlotTemplateAssets(@PathVariable Long id) {
        log.debug("REST request to delete SlotTemplateAssets : {}", id);
        slotTemplateAssetsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
