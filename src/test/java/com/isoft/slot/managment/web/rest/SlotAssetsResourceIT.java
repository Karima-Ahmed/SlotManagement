package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.SlotManagementApp;
import com.isoft.slot.managment.config.SecurityBeanOverrideConfiguration;
import com.isoft.slot.managment.domain.SlotAssets;
import com.isoft.slot.managment.repository.SlotAssetsRepository;
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
import java.util.List;

import static com.isoft.slot.managment.web.rest.TestUtil.createFormattingConversionService;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link SlotAssetsResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, SlotManagementApp.class})
public class SlotAssetsResourceIT {

    @Autowired
    private SlotAssetsRepository slotAssetsRepository;

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

    private MockMvc restSlotAssetsMockMvc;

    private SlotAssets slotAssets;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SlotAssetsResource slotAssetsResource = new SlotAssetsResource(slotAssetsRepository);
        this.restSlotAssetsMockMvc = MockMvcBuilders.standaloneSetup(slotAssetsResource)
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
    public static SlotAssets createEntity(EntityManager em) {
        SlotAssets slotAssets = new SlotAssets();
        return slotAssets;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SlotAssets createUpdatedEntity(EntityManager em) {
        SlotAssets slotAssets = new SlotAssets();
        return slotAssets;
    }

    @BeforeEach
    public void initTest() {
        slotAssets = createEntity(em);
    }

    @Test
    @Transactional
    public void createSlotAssets() throws Exception {
        int databaseSizeBeforeCreate = slotAssetsRepository.findAll().size();

        // Create the SlotAssets
        restSlotAssetsMockMvc.perform(post("/api/slot-assets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotAssets)))
            .andExpect(status().isCreated());

        // Validate the SlotAssets in the database
        List<SlotAssets> slotAssetsList = slotAssetsRepository.findAll();
        assertThat(slotAssetsList).hasSize(databaseSizeBeforeCreate + 1);
        SlotAssets testSlotAssets = slotAssetsList.get(slotAssetsList.size() - 1);
    }

    @Test
    @Transactional
    public void createSlotAssetsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = slotAssetsRepository.findAll().size();

        // Create the SlotAssets with an existing ID
        slotAssets.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSlotAssetsMockMvc.perform(post("/api/slot-assets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotAssets)))
            .andExpect(status().isBadRequest());

        // Validate the SlotAssets in the database
        List<SlotAssets> slotAssetsList = slotAssetsRepository.findAll();
        assertThat(slotAssetsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSlotAssets() throws Exception {
        // Initialize the database
        slotAssetsRepository.saveAndFlush(slotAssets);

        // Get all the slotAssetsList
        restSlotAssetsMockMvc.perform(get("/api/slot-assets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(slotAssets.getId().intValue())));
    }
    
    @Test
    @Transactional
    public void getSlotAssets() throws Exception {
        // Initialize the database
        slotAssetsRepository.saveAndFlush(slotAssets);

        // Get the slotAssets
        restSlotAssetsMockMvc.perform(get("/api/slot-assets/{id}", slotAssets.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(slotAssets.getId().intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSlotAssets() throws Exception {
        // Get the slotAssets
        restSlotAssetsMockMvc.perform(get("/api/slot-assets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSlotAssets() throws Exception {
        // Initialize the database
        slotAssetsRepository.saveAndFlush(slotAssets);

        int databaseSizeBeforeUpdate = slotAssetsRepository.findAll().size();

        // Update the slotAssets
        SlotAssets updatedSlotAssets = slotAssetsRepository.findById(slotAssets.getId()).get();
        // Disconnect from session so that the updates on updatedSlotAssets are not directly saved in db
        em.detach(updatedSlotAssets);

        restSlotAssetsMockMvc.perform(put("/api/slot-assets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSlotAssets)))
            .andExpect(status().isOk());

        // Validate the SlotAssets in the database
        List<SlotAssets> slotAssetsList = slotAssetsRepository.findAll();
        assertThat(slotAssetsList).hasSize(databaseSizeBeforeUpdate);
        SlotAssets testSlotAssets = slotAssetsList.get(slotAssetsList.size() - 1);
    }

    @Test
    @Transactional
    public void updateNonExistingSlotAssets() throws Exception {
        int databaseSizeBeforeUpdate = slotAssetsRepository.findAll().size();

        // Create the SlotAssets

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSlotAssetsMockMvc.perform(put("/api/slot-assets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotAssets)))
            .andExpect(status().isBadRequest());

        // Validate the SlotAssets in the database
        List<SlotAssets> slotAssetsList = slotAssetsRepository.findAll();
        assertThat(slotAssetsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSlotAssets() throws Exception {
        // Initialize the database
        slotAssetsRepository.saveAndFlush(slotAssets);

        int databaseSizeBeforeDelete = slotAssetsRepository.findAll().size();

        // Delete the slotAssets
        restSlotAssetsMockMvc.perform(delete("/api/slot-assets/{id}", slotAssets.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SlotAssets> slotAssetsList = slotAssetsRepository.findAll();
        assertThat(slotAssetsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
