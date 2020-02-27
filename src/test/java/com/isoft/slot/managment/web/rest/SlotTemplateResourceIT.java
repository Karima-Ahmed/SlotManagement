package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.SlotManagementApp;
import com.isoft.slot.managment.config.SecurityBeanOverrideConfiguration;
import com.isoft.slot.managment.domain.SlotTemplate;
import com.isoft.slot.managment.repository.SlotTemplateRepository;
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
 * Integration tests for the {@link SlotTemplateResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, SlotManagementApp.class})
public class SlotTemplateResourceIT {

    private static final BigDecimal DEFAULT_FACILITATOR_NO = new BigDecimal(1);
    private static final BigDecimal UPDATED_FACILITATOR_NO = new BigDecimal(2);

    private static final BigDecimal DEFAULT_FACILITATOR_TYPE = new BigDecimal(1);
    private static final BigDecimal UPDATED_FACILITATOR_TYPE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CAPACITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_CAPACITY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TIME_FRAME = new BigDecimal(1);
    private static final BigDecimal UPDATED_TIME_FRAME = new BigDecimal(2);

    private static final BigDecimal DEFAULT_ASSET_TYPE = new BigDecimal(1);
    private static final BigDecimal UPDATED_ASSET_TYPE = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BREAK_TIME = new BigDecimal(1);
    private static final BigDecimal UPDATED_BREAK_TIME = new BigDecimal(2);

    @Autowired
    private SlotTemplateRepository slotTemplateRepository;

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

    private MockMvc restSlotTemplateMockMvc;

    private SlotTemplate slotTemplate;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SlotTemplateResource slotTemplateResource = new SlotTemplateResource(slotTemplateRepository);
        this.restSlotTemplateMockMvc = MockMvcBuilders.standaloneSetup(slotTemplateResource)
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
    public static SlotTemplate createEntity(EntityManager em) {
        SlotTemplate slotTemplate = new SlotTemplate()
            .facilitatorNo(DEFAULT_FACILITATOR_NO)
            .facilitatorType(DEFAULT_FACILITATOR_TYPE)
            .capacity(DEFAULT_CAPACITY)
            .timeFrame(DEFAULT_TIME_FRAME)
            .assetType(DEFAULT_ASSET_TYPE)
            .breakTime(DEFAULT_BREAK_TIME);
        return slotTemplate;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SlotTemplate createUpdatedEntity(EntityManager em) {
        SlotTemplate slotTemplate = new SlotTemplate()
            .facilitatorNo(UPDATED_FACILITATOR_NO)
            .facilitatorType(UPDATED_FACILITATOR_TYPE)
            .capacity(UPDATED_CAPACITY)
            .timeFrame(UPDATED_TIME_FRAME)
            .assetType(UPDATED_ASSET_TYPE)
            .breakTime(UPDATED_BREAK_TIME);
        return slotTemplate;
    }

    @BeforeEach
    public void initTest() {
        slotTemplate = createEntity(em);
    }

    @Test
    @Transactional
    public void createSlotTemplate() throws Exception {
        int databaseSizeBeforeCreate = slotTemplateRepository.findAll().size();

        // Create the SlotTemplate
        restSlotTemplateMockMvc.perform(post("/api/slot-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplate)))
            .andExpect(status().isCreated());

        // Validate the SlotTemplate in the database
        List<SlotTemplate> slotTemplateList = slotTemplateRepository.findAll();
        assertThat(slotTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        SlotTemplate testSlotTemplate = slotTemplateList.get(slotTemplateList.size() - 1);
        assertThat(testSlotTemplate.getFacilitatorNo()).isEqualTo(DEFAULT_FACILITATOR_NO);
        assertThat(testSlotTemplate.getFacilitatorType()).isEqualTo(DEFAULT_FACILITATOR_TYPE);
        assertThat(testSlotTemplate.getCapacity()).isEqualTo(DEFAULT_CAPACITY);
        assertThat(testSlotTemplate.getTimeFrame()).isEqualTo(DEFAULT_TIME_FRAME);
        assertThat(testSlotTemplate.getAssetType()).isEqualTo(DEFAULT_ASSET_TYPE);
        assertThat(testSlotTemplate.getBreakTime()).isEqualTo(DEFAULT_BREAK_TIME);
    }

    @Test
    @Transactional
    public void createSlotTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = slotTemplateRepository.findAll().size();

        // Create the SlotTemplate with an existing ID
        slotTemplate.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSlotTemplateMockMvc.perform(post("/api/slot-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplate)))
            .andExpect(status().isBadRequest());

        // Validate the SlotTemplate in the database
        List<SlotTemplate> slotTemplateList = slotTemplateRepository.findAll();
        assertThat(slotTemplateList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSlotTemplates() throws Exception {
        // Initialize the database
        slotTemplateRepository.saveAndFlush(slotTemplate);

        // Get all the slotTemplateList
        restSlotTemplateMockMvc.perform(get("/api/slot-templates?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(slotTemplate.getId().intValue())))
            .andExpect(jsonPath("$.[*].facilitatorNo").value(hasItem(DEFAULT_FACILITATOR_NO.intValue())))
            .andExpect(jsonPath("$.[*].facilitatorType").value(hasItem(DEFAULT_FACILITATOR_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].capacity").value(hasItem(DEFAULT_CAPACITY.intValue())))
            .andExpect(jsonPath("$.[*].timeFrame").value(hasItem(DEFAULT_TIME_FRAME.intValue())))
            .andExpect(jsonPath("$.[*].assetType").value(hasItem(DEFAULT_ASSET_TYPE.intValue())))
            .andExpect(jsonPath("$.[*].breakTime").value(hasItem(DEFAULT_BREAK_TIME.intValue())));
    }
    
    @Test
    @Transactional
    public void getSlotTemplate() throws Exception {
        // Initialize the database
        slotTemplateRepository.saveAndFlush(slotTemplate);

        // Get the slotTemplate
        restSlotTemplateMockMvc.perform(get("/api/slot-templates/{id}", slotTemplate.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(slotTemplate.getId().intValue()))
            .andExpect(jsonPath("$.facilitatorNo").value(DEFAULT_FACILITATOR_NO.intValue()))
            .andExpect(jsonPath("$.facilitatorType").value(DEFAULT_FACILITATOR_TYPE.intValue()))
            .andExpect(jsonPath("$.capacity").value(DEFAULT_CAPACITY.intValue()))
            .andExpect(jsonPath("$.timeFrame").value(DEFAULT_TIME_FRAME.intValue()))
            .andExpect(jsonPath("$.assetType").value(DEFAULT_ASSET_TYPE.intValue()))
            .andExpect(jsonPath("$.breakTime").value(DEFAULT_BREAK_TIME.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSlotTemplate() throws Exception {
        // Get the slotTemplate
        restSlotTemplateMockMvc.perform(get("/api/slot-templates/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSlotTemplate() throws Exception {
        // Initialize the database
        slotTemplateRepository.saveAndFlush(slotTemplate);

        int databaseSizeBeforeUpdate = slotTemplateRepository.findAll().size();

        // Update the slotTemplate
        SlotTemplate updatedSlotTemplate = slotTemplateRepository.findById(slotTemplate.getId()).get();
        // Disconnect from session so that the updates on updatedSlotTemplate are not directly saved in db
        em.detach(updatedSlotTemplate);
        updatedSlotTemplate
            .facilitatorNo(UPDATED_FACILITATOR_NO)
            .facilitatorType(UPDATED_FACILITATOR_TYPE)
            .capacity(UPDATED_CAPACITY)
            .timeFrame(UPDATED_TIME_FRAME)
            .assetType(UPDATED_ASSET_TYPE)
            .breakTime(UPDATED_BREAK_TIME);

        restSlotTemplateMockMvc.perform(put("/api/slot-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSlotTemplate)))
            .andExpect(status().isOk());

        // Validate the SlotTemplate in the database
        List<SlotTemplate> slotTemplateList = slotTemplateRepository.findAll();
        assertThat(slotTemplateList).hasSize(databaseSizeBeforeUpdate);
        SlotTemplate testSlotTemplate = slotTemplateList.get(slotTemplateList.size() - 1);
        assertThat(testSlotTemplate.getFacilitatorNo()).isEqualTo(UPDATED_FACILITATOR_NO);
        assertThat(testSlotTemplate.getFacilitatorType()).isEqualTo(UPDATED_FACILITATOR_TYPE);
        assertThat(testSlotTemplate.getCapacity()).isEqualTo(UPDATED_CAPACITY);
        assertThat(testSlotTemplate.getTimeFrame()).isEqualTo(UPDATED_TIME_FRAME);
        assertThat(testSlotTemplate.getAssetType()).isEqualTo(UPDATED_ASSET_TYPE);
        assertThat(testSlotTemplate.getBreakTime()).isEqualTo(UPDATED_BREAK_TIME);
    }

    @Test
    @Transactional
    public void updateNonExistingSlotTemplate() throws Exception {
        int databaseSizeBeforeUpdate = slotTemplateRepository.findAll().size();

        // Create the SlotTemplate

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSlotTemplateMockMvc.perform(put("/api/slot-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplate)))
            .andExpect(status().isBadRequest());

        // Validate the SlotTemplate in the database
        List<SlotTemplate> slotTemplateList = slotTemplateRepository.findAll();
        assertThat(slotTemplateList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSlotTemplate() throws Exception {
        // Initialize the database
        slotTemplateRepository.saveAndFlush(slotTemplate);

        int databaseSizeBeforeDelete = slotTemplateRepository.findAll().size();

        // Delete the slotTemplate
        restSlotTemplateMockMvc.perform(delete("/api/slot-templates/{id}", slotTemplate.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SlotTemplate> slotTemplateList = slotTemplateRepository.findAll();
        assertThat(slotTemplateList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
