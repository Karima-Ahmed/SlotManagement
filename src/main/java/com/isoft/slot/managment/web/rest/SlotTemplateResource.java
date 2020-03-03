package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.service.SlotTemplateService;
import com.isoft.slot.managment.web.rest.errors.BadRequestAlertException;
import com.isoft.slot.managment.service.dto.SlotTemplateDTO;

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
 * REST controller for managing {@link com.isoft.slot.managment.domain.SlotTemplate}.
 */
@RestController
@RequestMapping("/api")
public class SlotTemplateResource {

    private final Logger log = LoggerFactory.getLogger(SlotTemplateResource.class);

    private static final String ENTITY_NAME = "slotManagementSlotTemplate";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final SlotTemplateService slotTemplateService;

    public SlotTemplateResource(SlotTemplateService slotTemplateService) {
        this.slotTemplateService = slotTemplateService;
    }

    /**
     * {@code POST  /slot-templates} : Create a new slotTemplate.
     *
     * @param slotTemplateDTO the slotTemplateDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new slotTemplateDTO, or with status {@code 400 (Bad Request)} if the slotTemplate has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/slot-templates")
    public ResponseEntity<SlotTemplateDTO> createSlotTemplate(@RequestBody SlotTemplateDTO slotTemplateDTO) throws URISyntaxException {
        log.debug("REST request to save SlotTemplate : {}", slotTemplateDTO);
        if (slotTemplateDTO.getId() != null) {
            throw new BadRequestAlertException("A new slotTemplate cannot already have an ID", ENTITY_NAME, "idexists");
        }
        SlotTemplateDTO result = slotTemplateService.save(slotTemplateDTO);
        return ResponseEntity.created(new URI("/api/slot-templates/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /slot-templates} : Updates an existing slotTemplate.
     *
     * @param slotTemplateDTO the slotTemplateDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated slotTemplateDTO,
     * or with status {@code 400 (Bad Request)} if the slotTemplateDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the slotTemplateDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/slot-templates")
    public ResponseEntity<SlotTemplateDTO> updateSlotTemplate(@RequestBody SlotTemplateDTO slotTemplateDTO) throws URISyntaxException {
        log.debug("REST request to update SlotTemplate : {}", slotTemplateDTO);
        if (slotTemplateDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        SlotTemplateDTO result = slotTemplateService.save(slotTemplateDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, slotTemplateDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /slot-templates} : get all the slotTemplates.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of slotTemplates in body.
     */
    @GetMapping("/slot-templates")
    public List<SlotTemplateDTO> getAllSlotTemplates() {
        log.debug("REST request to get all SlotTemplates");
        return slotTemplateService.findAll();
    }

    /**
     * {@code GET  /slot-templates/:id} : get the "id" slotTemplate.
     *
     * @param id the id of the slotTemplateDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the slotTemplateDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/slot-templates/{id}")
    public ResponseEntity<SlotTemplateDTO> getSlotTemplate(@PathVariable Long id) {
        log.debug("REST request to get SlotTemplate : {}", id);
        Optional<SlotTemplateDTO> slotTemplateDTO = slotTemplateService.findOne(id);
        return ResponseUtil.wrapOrNotFound(slotTemplateDTO);
    }

    /**
     * {@code DELETE  /slot-templates/:id} : delete the "id" slotTemplate.
     *
     * @param id the id of the slotTemplateDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/slot-templates/{id}")
    public ResponseEntity<Void> deleteSlotTemplate(@PathVariable Long id) {
        log.debug("REST request to delete SlotTemplate : {}", id);
        slotTemplateService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
