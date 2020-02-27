package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.SlotManagementApp;
import com.isoft.slot.managment.config.SecurityBeanOverrideConfiguration;
import com.isoft.slot.managment.domain.SlotInstance;
import com.isoft.slot.managment.repository.SlotInstanceRepository;
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
import java.time.LocalDate;
import java.time.ZoneId;
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

    private static final BigDecimal DEFAULT_USER_ID = new BigDecimal(1);
    private static final BigDecimal UPDATED_USER_ID = new BigDecimal(2);

    private static final LocalDate DEFAULT_TIME_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIME_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TIME_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIME_TO = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_ASSET_ID = new BigDecimal(1);
    private static final BigDecimal UPDATED_ASSET_ID = new BigDecimal(2);

    @Autowired
    private SlotInstanceRepository slotInstanceRepository;

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
        final SlotInstanceResource slotInstanceResource = new SlotInstanceResource(slotInstanceRepository);
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
            .userId(DEFAULT_USER_ID)
            .timeFrom(DEFAULT_TIME_FROM)
            .timeTo(DEFAULT_TIME_TO)
            .assetId(DEFAULT_ASSET_ID);
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
            .userId(UPDATED_USER_ID)
            .timeFrom(UPDATED_TIME_FROM)
            .timeTo(UPDATED_TIME_TO)
            .assetId(UPDATED_ASSET_ID);
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
        restSlotInstanceMockMvc.perform(post("/api/slot-instances")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotInstance)))
            .andExpect(status().isCreated());

        // Validate the SlotInstance in the database
        List<SlotInstance> slotInstanceList = slotInstanceRepository.findAll();
        assertThat(slotInstanceList).hasSize(databaseSizeBeforeCreate + 1);
        SlotInstance testSlotInstance = slotInstanceList.get(slotInstanceList.size() - 1);
        assertThat(testSlotInstance.getUserId()).isEqualTo(DEFAULT_USER_ID);
        assertThat(testSlotInstance.getTimeFrom()).isEqualTo(DEFAULT_TIME_FROM);
        assertThat(testSlotInstance.getTimeTo()).isEqualTo(DEFAULT_TIME_TO);
        assertThat(testSlotInstance.getAssetId()).isEqualTo(DEFAULT_ASSET_ID);
    }

    @Test
    @Transactional
    public void createSlotInstanceWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = slotInstanceRepository.findAll().size();

        // Create the SlotInstance with an existing ID
        slotInstance.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSlotInstanceMockMvc.perform(post("/api/slot-instances")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotInstance)))
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
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())))
            .andExpect(jsonPath("$.[*].timeFrom").value(hasItem(DEFAULT_TIME_FROM.toString())))
            .andExpect(jsonPath("$.[*].timeTo").value(hasItem(DEFAULT_TIME_TO.toString())))
            .andExpect(jsonPath("$.[*].assetId").value(hasItem(DEFAULT_ASSET_ID.intValue())));
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
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()))
            .andExpect(jsonPath("$.timeFrom").value(DEFAULT_TIME_FROM.toString()))
            .andExpect(jsonPath("$.timeTo").value(DEFAULT_TIME_TO.toString()))
            .andExpect(jsonPath("$.assetId").value(DEFAULT_ASSET_ID.intValue()));
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
            .userId(UPDATED_USER_ID)
            .timeFrom(UPDATED_TIME_FROM)
            .timeTo(UPDATED_TIME_TO)
            .assetId(UPDATED_ASSET_ID);

        restSlotInstanceMockMvc.perform(put("/api/slot-instances")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSlotInstance)))
            .andExpect(status().isOk());

        // Validate the SlotInstance in the database
        List<SlotInstance> slotInstanceList = slotInstanceRepository.findAll();
        assertThat(slotInstanceList).hasSize(databaseSizeBeforeUpdate);
        SlotInstance testSlotInstance = slotInstanceList.get(slotInstanceList.size() - 1);
        assertThat(testSlotInstance.getUserId()).isEqualTo(UPDATED_USER_ID);
        assertThat(testSlotInstance.getTimeFrom()).isEqualTo(UPDATED_TIME_FROM);
        assertThat(testSlotInstance.getTimeTo()).isEqualTo(UPDATED_TIME_TO);
        assertThat(testSlotInstance.getAssetId()).isEqualTo(UPDATED_ASSET_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingSlotInstance() throws Exception {
        int databaseSizeBeforeUpdate = slotInstanceRepository.findAll().size();

        // Create the SlotInstance

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSlotInstanceMockMvc.perform(put("/api/slot-instances")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotInstance)))
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
