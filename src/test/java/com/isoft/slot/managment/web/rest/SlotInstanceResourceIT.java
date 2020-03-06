package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.SlotManagementApp;
import com.isoft.slot.managment.config.SecurityBeanOverrideConfiguration;
import com.isoft.slot.managment.domain.SlotInstance;
import com.isoft.slot.managment.repository.SlotInstanceRepository;
import com.isoft.slot.managment.service.SlotInstanceService;
import com.isoft.slot.managment.service.dto.SlotInstanceDTO;
import com.isoft.slot.managment.service.mapper.SlotInstanceMapper;
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
 * Integration tests for the {@link SlotInstanceResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, SlotManagementApp.class})
public class SlotInstanceResourceIT {

    private static final String DEFAULT_DESC_AR = "AAAAAAAAAA";
    private static final String UPDATED_DESC_AR = "BBBBBBBBBB";

    private static final String DEFAULT_DESC_EN = "AAAAAAAAAA";
    private static final String UPDATED_DESC_EN = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TIME_FRAME = new BigDecimal(1);
    private static final BigDecimal UPDATED_TIME_FRAME = new BigDecimal(2);

    private static final BigDecimal DEFAULT_BREAK_TIME = new BigDecimal(1);
    private static final BigDecimal UPDATED_BREAK_TIME = new BigDecimal(2);

    private static final LocalDateTime DEFAULT_TIME_FROM = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
    private static final LocalDateTime UPDATED_TIME_FROM = LocalDateTime.now(ZoneId.systemDefault());

    private static final LocalDateTime DEFAULT_TIME_TO = LocalDateTime.ofEpochSecond(10000, 0, ZoneOffset.UTC);
    private static final LocalDateTime UPDATED_TIME_TO = LocalDateTime.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_CENTER_ID = new BigDecimal(1);
    private static final BigDecimal UPDATED_CENTER_ID = new BigDecimal(2);

    private static final BigDecimal DEFAULT_AVAILABLE_CAPACITY = new BigDecimal(1);
    private static final BigDecimal UPDATED_AVAILABLE_CAPACITY = new BigDecimal(2);

    @Autowired
    private SlotInstanceRepository slotInstanceRepository;

    @Autowired
    private SlotInstanceMapper slotInstanceMapper;

    @Autowired
    private SlotInstanceService slotInstanceService;

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

    private MockMvc restSlotInstanceMockMvc;

    private SlotInstance slotInstance;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SlotInstanceResource slotInstanceResource = new SlotInstanceResource(slotInstanceService);
        this.restSlotInstanceMockMvc = MockMvcBuilders.standaloneSetup(slotInstanceResource)
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
    public static SlotInstance createEntity(EntityManager em) {
        SlotInstance slotInstance = new SlotInstance()
            .descAr(DEFAULT_DESC_AR)
            .descEn(DEFAULT_DESC_EN)
            .timeFrame(DEFAULT_TIME_FRAME)
            .breakTime(DEFAULT_BREAK_TIME)
            .timeFrom(DEFAULT_TIME_FROM)
            .timeTo(DEFAULT_TIME_TO)
            .centerId(DEFAULT_CENTER_ID)
            .availableCapacity(DEFAULT_AVAILABLE_CAPACITY);
        return slotInstance;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SlotInstance createUpdatedEntity(EntityManager em) {
        SlotInstance slotInstance = new SlotInstance()
            .descAr(UPDATED_DESC_AR)
            .descEn(UPDATED_DESC_EN)
            .timeFrame(UPDATED_TIME_FRAME)
            .breakTime(UPDATED_BREAK_TIME)
            .timeFrom(UPDATED_TIME_FROM)
            .timeTo(UPDATED_TIME_TO)
            .centerId(UPDATED_CENTER_ID)
            .availableCapacity(UPDATED_AVAILABLE_CAPACITY);
        return slotInstance;
    }

    @BeforeEach
    public void initTest() {
        slotInstance = createEntity(em);
    }

    @Test
    @Transactional
    public void createSlotInstance() throws Exception {
        int databaseSizeBeforeCreate = slotInstanceRepository.findAll().size();

        // Create the SlotInstance
        SlotInstanceDTO slotInstanceDTO = slotInstanceMapper.toDto(slotInstance);
        restSlotInstanceMockMvc.perform(post("/api/slot-instances")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotInstanceDTO)))
            .andExpect(status().isCreated());

        // Validate the SlotInstance in the database
        List<SlotInstance> slotInstanceList = slotInstanceRepository.findAll();
        assertThat(slotInstanceList).hasSize(databaseSizeBeforeCreate + 1);
        SlotInstance testSlotInstance = slotInstanceList.get(slotInstanceList.size() - 1);
        assertThat(testSlotInstance.getDescAr()).isEqualTo(DEFAULT_DESC_AR);
        assertThat(testSlotInstance.getDescEn()).isEqualTo(DEFAULT_DESC_EN);
        assertThat(testSlotInstance.getTimeFrame()).isEqualTo(DEFAULT_TIME_FRAME);
        assertThat(testSlotInstance.getBreakTime()).isEqualTo(DEFAULT_BREAK_TIME);
        assertThat(testSlotInstance.getTimeFrom()).isEqualTo(DEFAULT_TIME_FROM);
        assertThat(testSlotInstance.getTimeTo()).isEqualTo(DEFAULT_TIME_TO);
        assertThat(testSlotInstance.getCenterId()).isEqualTo(DEFAULT_CENTER_ID);
        assertThat(testSlotInstance.getAvailableCapacity()).isEqualTo(DEFAULT_AVAILABLE_CAPACITY);
    }

    @Test
    @Transactional
    public void createSlotInstanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = slotInstanceRepository.findAll().size();

        // Create the SlotInstance with an existing ID
        slotInstance.setId(1L);
        SlotInstanceDTO slotInstanceDTO = slotInstanceMapper.toDto(slotInstance);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSlotInstanceMockMvc.perform(post("/api/slot-instances")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotInstanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SlotInstance in the database
        List<SlotInstance> slotInstanceList = slotInstanceRepository.findAll();
        assertThat(slotInstanceList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSlotInstances() throws Exception {
        // Initialize the database
        slotInstanceRepository.saveAndFlush(slotInstance);

        // Get all the slotInstanceList
        restSlotInstanceMockMvc.perform(get("/api/slot-instances?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(slotInstance.getId().intValue())))
            .andExpect(jsonPath("$.[*].descAr").value(hasItem(DEFAULT_DESC_AR)))
            .andExpect(jsonPath("$.[*].descEn").value(hasItem(DEFAULT_DESC_EN)))
            .andExpect(jsonPath("$.[*].timeFrame").value(hasItem(DEFAULT_TIME_FRAME.intValue())))
            .andExpect(jsonPath("$.[*].breakTime").value(hasItem(DEFAULT_BREAK_TIME.intValue())))
            .andExpect(jsonPath("$.[*].timeFrom").value(hasItem(DEFAULT_TIME_FROM.toString())))
            .andExpect(jsonPath("$.[*].timeTo").value(hasItem(DEFAULT_TIME_TO.toString())))
            .andExpect(jsonPath("$.[*].centerId").value(hasItem(DEFAULT_CENTER_ID.intValue())))
            .andExpect(jsonPath("$.[*].availableCapacity").value(hasItem(DEFAULT_AVAILABLE_CAPACITY.intValue())));
    }
    
    @Test
    @Transactional
    public void getSlotInstance() throws Exception {
        // Initialize the database
        slotInstanceRepository.saveAndFlush(slotInstance);

        // Get the slotInstance
        restSlotInstanceMockMvc.perform(get("/api/slot-instances/{id}", slotInstance.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(slotInstance.getId().intValue()))
            .andExpect(jsonPath("$.descAr").value(DEFAULT_DESC_AR))
            .andExpect(jsonPath("$.descEn").value(DEFAULT_DESC_EN))
            .andExpect(jsonPath("$.timeFrame").value(DEFAULT_TIME_FRAME.intValue()))
            .andExpect(jsonPath("$.breakTime").value(DEFAULT_BREAK_TIME.intValue()))
            .andExpect(jsonPath("$.timeFrom").value(DEFAULT_TIME_FROM.toString()))
            .andExpect(jsonPath("$.timeTo").value(DEFAULT_TIME_TO.toString()))
            .andExpect(jsonPath("$.centerId").value(DEFAULT_CENTER_ID.intValue()))
            .andExpect(jsonPath("$.availableCapacity").value(DEFAULT_AVAILABLE_CAPACITY.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSlotInstance() throws Exception {
        // Get the slotInstance
        restSlotInstanceMockMvc.perform(get("/api/slot-instances/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSlotInstance() throws Exception {
        // Initialize the database
        slotInstanceRepository.saveAndFlush(slotInstance);

        int databaseSizeBeforeUpdate = slotInstanceRepository.findAll().size();

        // Update the slotInstance
        SlotInstance updatedSlotInstance = slotInstanceRepository.findById(slotInstance.getId()).get();
        // Disconnect from session so that the updates on updatedSlotInstance are not directly saved in db
        em.detach(updatedSlotInstance);
        updatedSlotInstance
            .descAr(UPDATED_DESC_AR)
            .descEn(UPDATED_DESC_EN)
            .timeFrame(UPDATED_TIME_FRAME)
            .breakTime(UPDATED_BREAK_TIME)
            .timeFrom(UPDATED_TIME_FROM)
            .timeTo(UPDATED_TIME_TO)
            .centerId(UPDATED_CENTER_ID)
            .availableCapacity(UPDATED_AVAILABLE_CAPACITY);
        SlotInstanceDTO slotInstanceDTO = slotInstanceMapper.toDto(updatedSlotInstance);

        restSlotInstanceMockMvc.perform(put("/api/slot-instances")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotInstanceDTO)))
            .andExpect(status().isOk());

        // Validate the SlotInstance in the database
        List<SlotInstance> slotInstanceList = slotInstanceRepository.findAll();
        assertThat(slotInstanceList).hasSize(databaseSizeBeforeUpdate);
        SlotInstance testSlotInstance = slotInstanceList.get(slotInstanceList.size() - 1);
        assertThat(testSlotInstance.getDescAr()).isEqualTo(UPDATED_DESC_AR);
        assertThat(testSlotInstance.getDescEn()).isEqualTo(UPDATED_DESC_EN);
        assertThat(testSlotInstance.getTimeFrame()).isEqualTo(UPDATED_TIME_FRAME);
        assertThat(testSlotInstance.getBreakTime()).isEqualTo(UPDATED_BREAK_TIME);
        assertThat(testSlotInstance.getTimeFrom()).isEqualTo(UPDATED_TIME_FROM);
        assertThat(testSlotInstance.getTimeTo()).isEqualTo(UPDATED_TIME_TO);
        assertThat(testSlotInstance.getCenterId()).isEqualTo(UPDATED_CENTER_ID);
        assertThat(testSlotInstance.getAvailableCapacity()).isEqualTo(UPDATED_AVAILABLE_CAPACITY);
    }

    @Test
    @Transactional
    public void updateNonExistingSlotInstance() throws Exception {
        int databaseSizeBeforeUpdate = slotInstanceRepository.findAll().size();

        // Create the SlotInstance
        SlotInstanceDTO slotInstanceDTO = slotInstanceMapper.toDto(slotInstance);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSlotInstanceMockMvc.perform(put("/api/slot-instances")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotInstanceDTO)))
            .andExpect(status().isBadRequest());

        // Validate the SlotInstance in the database
        List<SlotInstance> slotInstanceList = slotInstanceRepository.findAll();
        assertThat(slotInstanceList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSlotInstance() throws Exception {
        // Initialize the database
        slotInstanceRepository.saveAndFlush(slotInstance);

        int databaseSizeBeforeDelete = slotInstanceRepository.findAll().size();

        // Delete the slotInstance
        restSlotInstanceMockMvc.perform(delete("/api/slot-instances/{id}", slotInstance.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SlotInstance> slotInstanceList = slotInstanceRepository.findAll();
        assertThat(slotInstanceList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
