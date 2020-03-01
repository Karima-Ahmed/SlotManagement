package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.SlotManagementApp;
import com.isoft.slot.managment.config.SecurityBeanOverrideConfiguration;
import com.isoft.slot.managment.domain.Assets;
import com.isoft.slot.managment.repository.AssetsRepository;
import com.isoft.slot.managment.web.rest.errors.ExceptionTranslator;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Validator;

import javax.persistence.EntityManager;
import java.math.BigDecimal;
import java.util.List;

import static com.isoft.slot.managment.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link AssetsResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, SlotManagementApp.class})
public class AssetsResourceIT {

    private static final BigDecimal DEFAULT_TYPE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TYPE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CENTER_ID = new BigDecimal(1);
    private static final BigDecimal UPDATED_CENTER_ID = new BigDecimal(2);

    @Autowired
    private AssetsRepository assetsRepository;

    @Autowired
    private MappingJackson2HttpMessageConverter jacksonMessageConverter;

    @Autowired
    private PageableHandlerMethodArgumentResolver pageableArgumentResolver;

    @Autowired
    private ExceptionTranslator exceptionTranslator;

    @Autowired
    private EntityManager em;

    @Autowired
    private Validator validator;

    private MockMvc restAssetsMockMvc;

    private Assets assets;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final AssetsResource assetsResource = new AssetsResource(assetsRepository);
        this.restAssetsMockMvc = MockMvcBuilders.standaloneSetup(assetsResource)
            .setCustomArgumentResolvers(pageableArgumentResolver)
            .setControllerAdvice(exceptionTranslator)
            .setConversionService(createFormattingConversionService())
            .setMessageConverters(jacksonMessageConverter)
            .setValidator(validator).build();
    }

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Assets createEntity(EntityManager em) {
        Assets assets = new Assets()
            .type(DEFAULT_TYPE)
            .centerId(DEFAULT_CENTER_ID);
        return assets;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Assets createUpdatedEntity(EntityManager em) {
        Assets assets = new Assets()
            .type(UPDATED_TYPE)
            .centerId(UPDATED_CENTER_ID);
        return assets;
    }

    @BeforeEach
    public void initTest() {
        assets = createEntity(em);
    }

    @Test
    @Transactional
    public void createAssets() throws Exception {
        int databaseSizeBeforeCreate = assetsRepository.findAll().size();

        // Create the Assets
        restAssetsMockMvc.perform(post("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(assets)))
            .andExpect(status().isCreated());

        // Validate the Assets in the database
        List<Assets> assetsList = assetsRepository.findAll();
        assertThat(assetsList).hasSize(databaseSizeBeforeCreate + 1);
        Assets testAssets = assetsList.get(assetsList.size() - 1);
        assertThat(testAssets.getType()).isEqualTo(DEFAULT_TYPE);
        assertThat(testAssets.getCenterId()).isEqualTo(DEFAULT_CENTER_ID);
    }

    @Test
    @Transactional
    public void createAssetsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = assetsRepository.findAll().size();

        // Create the Assets with an existing ID
        assets.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restAssetsMockMvc.perform(post("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(assets)))
            .andExpect(status().isBadRequest());

        // Validate the Assets in the database
        List<Assets> assetsList = assetsRepository.findAll();
        assertThat(assetsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllAssets() throws Exception {
        // Initialize the database
        assetsRepository.saveAndFlush(assets);

        // Get all the assetsList
        restAssetsMockMvc.perform(get("/api/assets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(assets.getId().intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].centerId").value(hasItem(DEFAULT_CENTER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getAssets() throws Exception {
        // Initialize the database
        assetsRepository.saveAndFlush(assets);

        // Get the assets
        restAssetsMockMvc.perform(get("/api/assets/{id}", assets.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(assets.getId().intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.intValue()))
            .andExpect(jsonPath("$.centerId").value(DEFAULT_CENTER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingAssets() throws Exception {
        // Get the assets
        restAssetsMockMvc.perform(get("/api/assets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateAssets() throws Exception {
        // Initialize the database
        assetsRepository.saveAndFlush(assets);

        int databaseSizeBeforeUpdate = assetsRepository.findAll().size();

        // Update the assets
        Assets updatedAssets = assetsRepository.findById(assets.getId()).get();
        // Disconnect from session so that the updates on updatedAssets are not directly saved in db
        em.detach(updatedAssets);
        updatedAssets
            .type(UPDATED_TYPE)
            .centerId(UPDATED_CENTER_ID);

        restAssetsMockMvc.perform(put("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedAssets)))
            .andExpect(status().isOk());

        // Validate the Assets in the database
        List<Assets> assetsList = assetsRepository.findAll();
        assertThat(assetsList).hasSize(databaseSizeBeforeUpdate);
        Assets testAssets = assetsList.get(assetsList.size() - 1);
        assertThat(testAssets.getType()).isEqualTo(UPDATED_TYPE);
        assertThat(testAssets.getCenterId()).isEqualTo(UPDATED_CENTER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingAssets() throws Exception {
        int databaseSizeBeforeUpdate = assetsRepository.findAll().size();

        // Create the Assets

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restAssetsMockMvc.perform(put("/api/assets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(assets)))
            .andExpect(status().isBadRequest());

        // Validate the Assets in the database
        List<Assets> assetsList = assetsRepository.findAll();
        assertThat(assetsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteAssets() throws Exception {
        // Initialize the database
        assetsRepository.saveAndFlush(assets);

        int databaseSizeBeforeDelete = assetsRepository.findAll().size();

        // Delete the assets
        restAssetsMockMvc.perform(delete("/api/assets/{id}", assets.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Assets> assetsList = assetsRepository.findAll();
        assertThat(assetsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
