package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.SlotManagementApp;
import com.isoft.slot.managment.config.SecurityBeanOverrideConfiguration;
import com.isoft.slot.managment.domain.SlotTemplate;
import com.isoft.slot.managment.repository.SlotTemplateRepository;
import com.isoft.slot.managment.service.SlotTemplateService;
import com.isoft.slot.managment.service.dto.SlotTemplateDTO;
import com.isoft.slot.managment.service.mapper.SlotTemplateMapper;
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
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
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

    private static final BigDecimal DEFAULT_CAPACITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_CAPACITY = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TIME_FRAME = new BigDecimal(1);
    private static final BigDecimal UPDATED_TIME_FRAME = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BREAK_TIME = new BigDecimal(1);
    private static final BigDecimal UPDATED_BREAK_TIME = new BigDecimal(2);

    private static final LocalDateTime DEFAULT_DAY_START_TIME = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
    private static final LocalDateTime UPDATED_DAY_START_TIME = LocalDateTime.now(ZoneId.systemDefault());

    private static final LocalDateTime DEFAULT_DAY_END_TIME = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
    private static final LocalDateTime UPDATED_DAY_END_TIME = LocalDateTime.now(ZoneId.systemDefault());

    private static final String DEFAULT_DESC_AR = "AAAAAAAAAA";
    private static final String UPDATED_DESC_AR = "BBBBBBBBBB";

    private static final String DEFAULT_DESC_EN = "AAAAAAAAAA";
    private static final String UPDATED_DESC_EN = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_CENTER_ID = new BigDecimal(1);
    private static final BigDecimal UPDATED_CENTER_ID = new BigDecimal(2);

    private static final BigDecimal DEFAULT_STATUS = new BigDecimal(1);
    private static final BigDecimal UPDATED_STATUS = new BigDecimal(2);

    @Autowired
    private SlotTemplateRepository slotTemplateRepository;

    @Autowired
    private SlotTemplateMapper slotTemplateMapper;

    @Autowired
    private SlotTemplateService slotTemplateService;

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
        final SlotTemplateResource slotTemplateResource = new SlotTemplateResource(slotTemplateService);
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
            .capacity(DEFAULT_CAPACITY)
            .timeFrame(DEFAULT_TIME_FRAME)
            .breakTime(DEFAULT_BREAK_TIME)
            .dayStartTime(DEFAULT_DAY_START_TIME)
            .dayEndTime(DEFAULT_DAY_END_TIME)
            .descAr(DEFAULT_DESC_AR)
            .descEn(DEFAULT_DESC_EN)
            .centerId(DEFAULT_CENTER_ID)
            .status(DEFAULT_STATUS);
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
            .capacity(UPDATED_CAPACITY)
            .timeFrame(UPDATED_TIME_FRAME)
            .breakTime(UPDATED_BREAK_TIME)
            .dayStartTime(UPDATED_DAY_START_TIME)
            .dayEndTime(UPDATED_DAY_END_TIME)
            .descAr(UPDATED_DESC_AR)
            .descEn(UPDATED_DESC_EN)
            .centerId(UPDATED_CENTER_ID)
            .status(UPDATED_STATUS);
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
        SlotTemplateDTO slotTemplateDTO = slotTemplateMapper.toDto(slotTemplate);
        restSlotTemplateMockMvc.perform(post("/api/slot-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplateDTO)))
            .andExpect(status().isCreated());

        // Validate the SlotTemplate in the database
        List<SlotTemplate> slotTemplateList = slotTemplateRepository.findAll();
        assertThat(slotTemplateList).hasSize(databaseSizeBeforeCreate + 1);
        SlotTemplate testSlotTemplate = slotTemplateList.get(slotTemplateList.size() - 1);
        assertThat(testSlotTemplate.getCapacity()).isEqualTo(DEFAULT_CAPACITY);
        assertThat(testSlotTemplate.getTimeFrame()).isEqualTo(DEFAULT_TIME_FRAME);
        assertThat(testSlotTemplate.getBreakTime()).isEqualTo(DEFAULT_BREAK_TIME);
        assertThat(testSlotTemplate.getDayStartTime()).isEqualTo(DEFAULT_DAY_START_TIME);
        assertThat(testSlotTemplate.getDayEndTime()).isEqualTo(DEFAULT_DAY_END_TIME);
        assertThat(testSlotTemplate.getDescAr()).isEqualTo(DEFAULT_DESC_AR);
        assertThat(testSlotTemplate.getDescEn()).isEqualTo(DEFAULT_DESC_EN);
        assertThat(testSlotTemplate.getCenterId()).isEqualTo(DEFAULT_CENTER_ID);
        assertThat(testSlotTemplate.getStatus()).isEqualTo(DEFAULT_STATUS);
    }

    @Test
    @Transactional
    public void createSlotTemplateWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = slotTemplateRepository.findAll().size();

        // Create the SlotTemplate with an existing ID
        slotTemplate.setId(1L);
        SlotTemplateDTO slotTemplateDTO = slotTemplateMapper.toDto(slotTemplate);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSlotTemplateMockMvc.perform(post("/api/slot-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplateDTO)))
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
            .andExpect(jsonPath("$.[*].capacity").value(hasItem(DEFAULT_CAPACITY.intValue())))
            .andExpect(jsonPath("$.[*].timeFrame").value(hasItem(DEFAULT_TIME_FRAME.intValue())))
            .andExpect(jsonPath("$.[*].breakTime").value(hasItem(DEFAULT_BREAK_TIME.intValue())))
            .andExpect(jsonPath("$.[*].dayStartTime").value(hasItem(DEFAULT_DAY_START_TIME.toString())))
            .andExpect(jsonPath("$.[*].dayEndTime").value(hasItem(DEFAULT_DAY_END_TIME.toString())))
            .andExpect(jsonPath("$.[*].descAr").value(hasItem(DEFAULT_DESC_AR)))
            .andExpect(jsonPath("$.[*].descEn").value(hasItem(DEFAULT_DESC_EN)))
            .andExpect(jsonPath("$.[*].centerId").value(hasItem(DEFAULT_CENTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())));
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
            .andExpect(jsonPath("$.capacity").value(DEFAULT_CAPACITY.intValue()))
            .andExpect(jsonPath("$.timeFrame").value(DEFAULT_TIME_FRAME.intValue()))
            .andExpect(jsonPath("$.breakTime").value(DEFAULT_BREAK_TIME.intValue()))
            .andExpect(jsonPath("$.dayStartTime").value(DEFAULT_DAY_START_TIME.toString()))
            .andExpect(jsonPath("$.dayEndTime").value(DEFAULT_DAY_END_TIME.toString()))
            .andExpect(jsonPath("$.descAr").value(DEFAULT_DESC_AR))
            .andExpect(jsonPath("$.descEn").value(DEFAULT_DESC_EN))
            .andExpect(jsonPath("$.centerId").value(DEFAULT_CENTER_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.intValue()));
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
            .capacity(UPDATED_CAPACITY)
            .timeFrame(UPDATED_TIME_FRAME)
            .breakTime(UPDATED_BREAK_TIME)
            .dayStartTime(UPDATED_DAY_START_TIME)
            .dayEndTime(UPDATED_DAY_END_TIME)
            .descAr(UPDATED_DESC_AR)
            .descEn(UPDATED_DESC_EN)
            .centerId(UPDATED_CENTER_ID)
            .status(UPDATED_STATUS);
        SlotTemplateDTO slotTemplateDTO = slotTemplateMapper.toDto(updatedSlotTemplate);

        restSlotTemplateMockMvc.perform(put("/api/slot-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplateDTO)))
            .andExpect(status().isOk());

        // Validate the SlotTemplate in the database
        List<SlotTemplate> slotTemplateList = slotTemplateRepository.findAll();
        assertThat(slotTemplateList).hasSize(databaseSizeBeforeUpdate);
        SlotTemplate testSlotTemplate = slotTemplateList.get(slotTemplateList.size() - 1);
        assertThat(testSlotTemplate.getCapacity()).isEqualTo(UPDATED_CAPACITY);
        assertThat(testSlotTemplate.getTimeFrame()).isEqualTo(UPDATED_TIME_FRAME);
        assertThat(testSlotTemplate.getBreakTime()).isEqualTo(UPDATED_BREAK_TIME);
        assertThat(testSlotTemplate.getDayStartTime()).isEqualTo(UPDATED_DAY_START_TIME);
        assertThat(testSlotTemplate.getDayEndTime()).isEqualTo(UPDATED_DAY_END_TIME);
        assertThat(testSlotTemplate.getDescAr()).isEqualTo(UPDATED_DESC_AR);
        assertThat(testSlotTemplate.getDescEn()).isEqualTo(UPDATED_DESC_EN);
        assertThat(testSlotTemplate.getCenterId()).isEqualTo(UPDATED_CENTER_ID);
        assertThat(testSlotTemplate.getStatus()).isEqualTo(UPDATED_STATUS);
    }

    @Test
    @Transactional
    public void updateNonExistingSlotTemplate() throws Exception {
        int databaseSizeBeforeUpdate = slotTemplateRepository.findAll().size();

        // Create the SlotTemplate
        SlotTemplateDTO slotTemplateDTO = slotTemplateMapper.toDto(slotTemplate);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSlotTemplateMockMvc.perform(put("/api/slot-templates")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplateDTO)))
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
