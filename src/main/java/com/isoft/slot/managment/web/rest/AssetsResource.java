package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.domain.Assets;
import com.isoft.slot.managment.repository.AssetsRepository;
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
 * REST controller for managing {@link com.isoft.slot.managment.domain.Assets}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AssetsResource {

    private final Logger log = LoggerFactory.getLogger(AssetsResource.class);

    private static final String ENTITY_NAME = "slotManagementAssets";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AssetsRepository assetsRepository;

    public AssetsResource(AssetsRepository assetsRepository) {
        this.assetsRepository = assetsRepository;
    }

    /**
     * {@code POST  /assets} : Create a new assets.
     *
     * @param assets the assets to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new assets, or with status {@code 400 (Bad Request)} if the assets has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/assets")
    public ResponseEntity<Assets> createAssets(@RequestBody Assets assets) throws URISyntaxException {
        log.debug("REST request to save Assets : {}", assets);
        if (assets.getId() != null) {
            throw new BadRequestAlertException("A new assets cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Assets result = assetsRepository.save(assets);
        return ResponseEntity.created(new URI("/api/assets/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /assets} : Updates an existing assets.
     *
     * @param assets the assets to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated assets,
     * or with status {@code 400 (Bad Request)} if the assets is not valid,
     * or with status {@code 500 (Internal Server Error)} if the assets couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/assets")
    public ResponseEntity<Assets> updateAssets(@RequestBody Assets assets) throws URISyntaxException {
        log.debug("REST request to update Assets : {}", assets);
        if (assets.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Assets result = assetsRepository.save(assets);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, assets.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /assets} : get all the assets.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of assets in body.
     */
    @GetMapping("/assets")
    public List<Assets> getAllAssets() {
        log.debug("REST request to get all Assets");
        return assetsRepository.findAll();
    }

    /**
     * {@code GET  /assets/:id} : get the "id" assets.
     *
     * @param id the id of the assets to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the assets, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/assets/{id}")
    public ResponseEntity<Assets> getAssets(@PathVariable Long id) {
        log.debug("REST request to get Assets : {}", id);
        Optional<Assets> assets = assetsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(assets);
    }

    /**
     * {@code DELETE  /assets/:id} : delete the "id" assets.
     *
     * @param id the id of the assets to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/assets/{id}")
    public ResponseEntity<Void> deleteAssets(@PathVariable Long id) {
        log.debug("REST request to delete Assets : {}", id);
        assetsRepository.deleteById(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
