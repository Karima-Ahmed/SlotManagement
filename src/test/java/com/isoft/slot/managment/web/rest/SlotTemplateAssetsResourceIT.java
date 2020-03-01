package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.SlotManagementApp;
import com.isoft.slot.managment.config.SecurityBeanOverrideConfiguration;
import com.isoft.slot.managment.domain.SlotTemplateAssets;
import com.isoft.slot.managment.repository.SlotTemplateAssetsRepository;
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
 * Integration tests for the {@link SlotTemplateAssetsResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, SlotManagementApp.class})
public class SlotTemplateAssetsResourceIT {

    private static final BigDecimal DEFAULT_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TYPE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TYPE = new BigDecimal(2);

    @Autowired
    private SlotTemplateAssetsRepository slotTemplateAssetsRepository;

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

    private MockMvc restSlotTemplateAssetsMockMvc;

    private SlotTemplateAssets slotTemplateAssets;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SlotTemplateAssetsResource slotTemplateAssetsResource = new SlotTemplateAssetsResource(slotTemplateAssetsRepository);
        this.restSlotTemplateAssetsMockMvc = MockMvcBuilders.standaloneSetup(slotTemplateAssetsResource)
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
    public static SlotTemplateAssets createEntity(EntityManager em) {
        SlotTemplateAssets slotTemplateAssets = new SlotTemplateAssets()
            .count(DEFAULT_COUNT)
            .type(DEFAULT_TYPE);
        return slotTemplateAssets;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SlotTemplateAssets createUpdatedEntity(EntityManager em) {
        SlotTemplateAssets slotTemplateAssets = new SlotTemplateAssets()
            .count(UPDATED_COUNT)
            .type(UPDATED_TYPE);
        return slotTemplateAssets;
    }

    @BeforeEach
    public void initTest() {
        slotTemplateAssets = createEntity(em);
    }

    @Test
    @Transactional
    public void createSlotTemplateAssets() throws Exception {
        int databaseSizeBeforeCreate = slotTemplateAssetsRepository.findAll().size();

        // Create the SlotTemplateAssets
        restSlotTemplateAssetsMockMvc.perform(post("/api/slot-template-assets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplateAssets)))
            .andExpect(status().isCreated());

        // Validate the SlotTemplateAssets in the database
        List<SlotTemplateAssets> slotTemplateAssetsList = slotTemplateAssetsRepository.findAll();
        assertThat(slotTemplateAssetsList).hasSize(databaseSizeBeforeCreate + 1);
        SlotTemplateAssets testSlotTemplateAssets = slotTemplateAssetsList.get(slotTemplateAssetsList.size() - 1);
        assertThat(testSlotTemplateAssets.getCount()).isEqualTo(DEFAULT_COUNT);
        assertThat(testSlotTemplateAssets.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createSlotTemplateAssetsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = slotTemplateAssetsRepository.findAll().size();

        // Create the SlotTemplateAssets with an existing ID
        slotTemplateAssets.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSlotTemplateAssetsMockMvc.perform(post("/api/slot-template-assets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplateAssets)))
            .andExpect(status().isBadRequest());

        // Validate the SlotTemplateAssets in the database
        List<SlotTemplateAssets> slotTemplateAssetsList = slotTemplateAssetsRepository.findAll();
        assertThat(slotTemplateAssetsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSlotTemplateAssets() throws Exception {
        // Initialize the database
        slotTemplateAssetsRepository.saveAndFlush(slotTemplateAssets);

        // Get all the slotTemplateAssetsList
        restSlotTemplateAssetsMockMvc.perform(get("/api/slot-template-assets?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(slotTemplateAssets.getId().intValue())))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.intValue())));
    }
    
    @Test
    @Transactional
    public void getSlotTemplateAssets() throws Exception {
        // Initialize the database
        slotTemplateAssetsRepository.saveAndFlush(slotTemplateAssets);

        // Get the slotTemplateAssets
        restSlotTemplateAssetsMockMvc.perform(get("/api/slot-template-assets/{id}", slotTemplateAssets.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(slotTemplateAssets.getId().intValue()))
            .andExpect(jsonPath("$.count").value(DEFAULT_COUNT.intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSlotTemplateAssets() throws Exception {
        // Get the slotTemplateAssets
        restSlotTemplateAssetsMockMvc.perform(get("/api/slot-template-assets/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSlotTemplateAssets() throws Exception {
        // Initialize the database
        slotTemplateAssetsRepository.saveAndFlush(slotTemplateAssets);

        int databaseSizeBeforeUpdate = slotTemplateAssetsRepository.findAll().size();

        // Update the slotTemplateAssets
        SlotTemplateAssets updatedSlotTemplateAssets = slotTemplateAssetsRepository.findById(slotTemplateAssets.getId()).get();
        // Disconnect from session so that the updates on updatedSlotTemplateAssets are not directly saved in db
        em.detach(updatedSlotTemplateAssets);
        updatedSlotTemplateAssets
            .count(UPDATED_COUNT)
            .type(UPDATED_TYPE);

        restSlotTemplateAssetsMockMvc.perform(put("/api/slot-template-assets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSlotTemplateAssets)))
            .andExpect(status().isOk());

        // Validate the SlotTemplateAssets in the database
        List<SlotTemplateAssets> slotTemplateAssetsList = slotTemplateAssetsRepository.findAll();
        assertThat(slotTemplateAssetsList).hasSize(databaseSizeBeforeUpdate);
        SlotTemplateAssets testSlotTemplateAssets = slotTemplateAssetsList.get(slotTemplateAssetsList.size() - 1);
        assertThat(testSlotTemplateAssets.getCount()).isEqualTo(UPDATED_COUNT);
        assertThat(testSlotTemplateAssets.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingSlotTemplateAssets() throws Exception {
        int databaseSizeBeforeUpdate = slotTemplateAssetsRepository.findAll().size();

        // Create the SlotTemplateAssets

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSlotTemplateAssetsMockMvc.perform(put("/api/slot-template-assets")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplateAssets)))
            .andExpect(status().isBadRequest());

        // Validate the SlotTemplateAssets in the database
        List<SlotTemplateAssets> slotTemplateAssetsList = slotTemplateAssetsRepository.findAll();
        assertThat(slotTemplateAssetsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSlotTemplateAssets() throws Exception {
        // Initialize the database
        slotTemplateAssetsRepository.saveAndFlush(slotTemplateAssets);

        int databaseSizeBeforeDelete = slotTemplateAssetsRepository.findAll().size();

        // Delete the slotTemplateAssets
        restSlotTemplateAssetsMockMvc.perform(delete("/api/slot-template-assets/{id}", slotTemplateAssets.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SlotTemplateAssets> slotTemplateAssetsList = slotTemplateAssetsRepository.findAll();
        assertThat(slotTemplateAssetsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
