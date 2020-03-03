package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.service.AssetsService;
import com.isoft.slot.managment.web.rest.errors.BadRequestAlertException;
import com.isoft.slot.managment.service.dto.AssetsDTO;

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
 * REST controller for managing {@link com.isoft.slot.managment.domain.Assets}.
 */
@RestController
@RequestMapping("/api")
public class AssetsResource {

    private final Logger log = LoggerFactory.getLogger(AssetsResource.class);

    private static final String ENTITY_NAME = "slotManagementAssets";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AssetsService assetsService;

    public AssetsResource(AssetsService assetsService) {
        this.assetsService = assetsService;
    }

    /**
     * {@code POST  /assets} : Create a new assets.
     *
     * @param assetsDTO the assetsDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new assetsDTO, or with status {@code 400 (Bad Request)} if the assets has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/assets")
    public ResponseEntity<AssetsDTO> createAssets(@RequestBody AssetsDTO assetsDTO) throws URISyntaxException {
        log.debug("REST request to save Assets : {}", assetsDTO);
        if (assetsDTO.getId() != null) {
            throw new BadRequestAlertException("A new assets cannot already have an ID", ENTITY_NAME, "idexists");
        }
        AssetsDTO result = assetsService.save(assetsDTO);
        return ResponseEntity.created(new URI("/api/assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /assets} : Updates an existing assets.
     *
     * @param assetsDTO the assetsDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assetsDTO,
     * or with status {@code 400 (Bad Request)} if the assetsDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the assetsDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/assets")
    public ResponseEntity<AssetsDTO> updateAssets(@RequestBody AssetsDTO assetsDTO) throws URISyntaxException {
        log.debug("REST request to update Assets : {}", assetsDTO);
        if (assetsDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        AssetsDTO result = assetsService.save(assetsDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, assetsDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /assets} : get all the assets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assets in body.
     */
    @GetMapping("/assets")
    public List<AssetsDTO> getAllAssets() {
        log.debug("REST request to get all Assets");
        return assetsService.findAll();
    }

    /**
     * {@code GET  /assets/:id} : get the "id" assets.
     *
     * @param id the id of the assetsDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assetsDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assets/{id}")
    public ResponseEntity<AssetsDTO> getAssets(@PathVariable Long id) {
        log.debug("REST request to get Assets : {}", id);
        Optional<AssetsDTO> assetsDTO = assetsService.findOne(id);
        return ResponseUtil.wrapOrNotFound(assetsDTO);
    }

    /**
     * {@code DELETE  /assets/:id} : delete the "id" assets.
     *
     * @param id the id of the assetsDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/assets/{id}")
    public ResponseEntity<Void> deleteAssets(@PathVariable Long id) {
        log.debug("REST request to delete Assets : {}", id);
        assetsService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
