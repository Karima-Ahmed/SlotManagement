package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.service.SlotAssetsService;
import com.isoft.slot.managment.web.rest.errors.BadRequestAlertException;
import com.isoft.slot.managment.service.dto.SlotAssetsDTO;

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
 * REST controller for managing {@link com.isoft.slot.managment.domain.SlotAssets}.
 */
@RestController
@RequestMapping("/api")
public class SlotAssetsResource {

    private final Logger log = LoggerFactory.getLogger(SlotAssetsResource.class);

    private static final String ENTITY_NAME = "slotManagementSlotAssets";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SlotAssetsService slotAssetsService;

    public SlotAssetsResource(SlotAssetsService slotAssetsService) {
        this.slotAssetsService = slotAssetsService;
    }

    /**
     * {@code POST  /slot-assets} : Create a new slotAssets.
     *
     * @param slotAssetsDTO the slotAssetsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new slotAssetsDTO, or with status {@code 400 (Bad Request)} if the slotAssets has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/slot-assets")
    public ResponseEntity<SlotAssetsDTO> createSlotAssets(@RequestBody SlotAssetsDTO slotAssetsDTO) throws URISyntaxException {
        log.debug("REST request to save SlotAssets : {}", slotAssetsDTO);
        if (slotAssetsDTO.getId() != null) {
            throw new BadRequestAlertException("A new slotAssets cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlotAssetsDTO result = slotAssetsService.save(slotAssetsDTO);
        return ResponseEntity.created(new URI("/api/slot-assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /slot-assets} : Updates an existing slotAssets.
     *
     * @param slotAssetsDTO the slotAssetsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slotAssetsDTO,
     * or with status {@code 400 (Bad Request)} if the slotAssetsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the slotAssetsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/slot-assets")
    public ResponseEntity<SlotAssetsDTO> updateSlotAssets(@RequestBody SlotAssetsDTO slotAssetsDTO) throws URISyntaxException {
        log.debug("REST request to update SlotAssets : {}", slotAssetsDTO);
        if (slotAssetsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SlotAssetsDTO result = slotAssetsService.save(slotAssetsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slotAssetsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /slot-assets} : get all the slotAssets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of slotAssets in body.
     */
    @GetMapping("/slot-assets")
    public List<SlotAssetsDTO> getAllSlotAssets() {
        log.debug("REST request to get all SlotAssets");
        return slotAssetsService.findAll();
    }

    /**
     * {@code GET  /slot-assets/:id} : get the "id" slotAssets.
     *
     * @param id the id of the slotAssetsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the slotAssetsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/slot-assets/{id}")
    public ResponseEntity<SlotAssetsDTO> getSlotAssets(@PathVariable Long id) {
        log.debug("REST request to get SlotAssets : {}", id);
        Optional<SlotAssetsDTO> slotAssetsDTO = slotAssetsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(slotAssetsDTO);
    }

    /**
     * {@code DELETE  /slot-assets/:id} : delete the "id" slotAssets.
     *
     * @param id the id of the slotAssetsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/slot-assets/{id}")
    public ResponseEntity<Void> deleteSlotAssets(@PathVariable Long id) {
        log.debug("REST request to delete SlotAssets : {}", id);
        slotAssetsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
