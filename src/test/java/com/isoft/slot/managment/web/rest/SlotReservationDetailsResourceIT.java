package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.SlotManagementApp;
import com.isoft.slot.managment.config.SecurityBeanOverrideConfiguration;
import com.isoft.slot.managment.domain.SlotReservationDetails;
import com.isoft.slot.managment.repository.SlotReservationDetailsRepository;
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
 * Integration tests for the {@link SlotReservationDetailsResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, SlotManagementApp.class})
public class SlotReservationDetailsResourceIT {

    private static final BigDecimal DEFAULT_APPLICANT_ID = new BigDecimal(1);
    private static final BigDecimal UPDATED_APPLICANT_ID = new BigDecimal(2);

    private static final BigDecimal DEFAULT_STATUS = new BigDecimal(1);
    private static final BigDecimal UPDATED_STATUS = new BigDecimal(2);

    private static final LocalDate DEFAULT_TIME_FROM = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIME_FROM = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_TIME_TO = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_TIME_TO = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_REQUEST_NO = new BigDecimal(1);
    private static final BigDecimal UPDATED_REQUEST_NO = new BigDecimal(2);

    @Autowired
    private SlotReservationDetailsRepository slotReservationDetailsRepository;

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

    private MockMvc restSlotReservationDetailsMockMvc;

    private SlotReservationDetails slotReservationDetails;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SlotReservationDetailsResource slotReservationDetailsResource = new SlotReservationDetailsResource(slotReservationDetailsRepository);
        this.restSlotReservationDetailsMockMvc = MockMvcBuilders.standaloneSetup(slotReservationDetailsResource)
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
    public static SlotReservationDetails createEntity(EntityManager em) {
        SlotReservationDetails slotReservationDetails = new SlotReservationDetails()
            .applicantId(DEFAULT_APPLICANT_ID)
            .status(DEFAULT_STATUS)
            .timeFrom(DEFAULT_TIME_FROM)
            .timeTo(DEFAULT_TIME_TO)
            .requestNo(DEFAULT_REQUEST_NO);
        return slotReservationDetails;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SlotReservationDetails createUpdatedEntity(EntityManager em) {
        SlotReservationDetails slotReservationDetails = new SlotReservationDetails()
            .applicantId(UPDATED_APPLICANT_ID)
            .status(UPDATED_STATUS)
            .timeFrom(UPDATED_TIME_FROM)
            .timeTo(UPDATED_TIME_TO)
            .requestNo(UPDATED_REQUEST_NO);
        return slotReservationDetails;
    }

    @BeforeEach
    public void initTest() {
        slotReservationDetails = createEntity(em);
    }

    @Test
    @Transactional
    public void createSlotReservationDetails() throws Exception {
        int databaseSizeBeforeCreate = slotReservationDetailsRepository.findAll().size();

        // Create the SlotReservationDetails
        restSlotReservationDetailsMockMvc.perform(post("/api/slot-reservation-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotReservationDetails)))
            .andExpect(status().isCreated());

        // Validate the SlotReservationDetails in the database
        List<SlotReservationDetails> slotReservationDetailsList = slotReservationDetailsRepository.findAll();
        assertThat(slotReservationDetailsList).hasSize(databaseSizeBeforeCreate + 1);
        SlotReservationDetails testSlotReservationDetails = slotReservationDetailsList.get(slotReservationDetailsList.size() - 1);
        assertThat(testSlotReservationDetails.getApplicantId()).isEqualTo(DEFAULT_APPLICANT_ID);
        assertThat(testSlotReservationDetails.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testSlotReservationDetails.getTimeFrom()).isEqualTo(DEFAULT_TIME_FROM);
        assertThat(testSlotReservationDetails.getTimeTo()).isEqualTo(DEFAULT_TIME_TO);
        assertThat(testSlotReservationDetails.getRequestNo()).isEqualTo(DEFAULT_REQUEST_NO);
    }

    @Test
    @Transactional
    public void createSlotReservationDetailsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = slotReservationDetailsRepository.findAll().size();

        // Create the SlotReservationDetails with an existing ID
        slotReservationDetails.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSlotReservationDetailsMockMvc.perform(post("/api/slot-reservation-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotReservationDetails)))
            .andExpect(status().isBadRequest());

        // Validate the SlotReservationDetails in the database
        List<SlotReservationDetails> slotReservationDetailsList = slotReservationDetailsRepository.findAll();
        assertThat(slotReservationDetailsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSlotReservationDetails() throws Exception {
        // Initialize the database
        slotReservationDetailsRepository.saveAndFlush(slotReservationDetails);

        // Get all the slotReservationDetailsList
        restSlotReservationDetailsMockMvc.perform(get("/api/slot-reservation-details?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(slotReservationDetails.getId().intValue())))
            .andExpect(jsonPath("$.[*].applicantId").value(hasItem(DEFAULT_APPLICANT_ID.intValue())))
            .andExpect(jsonPath("$.[*].status").value(hasItem(DEFAULT_STATUS.intValue())))
            .andExpect(jsonPath("$.[*].timeFrom").value(hasItem(DEFAULT_TIME_FROM.toString())))
            .andExpect(jsonPath("$.[*].timeTo").value(hasItem(DEFAULT_TIME_TO.toString())))
            .andExpect(jsonPath("$.[*].requestNo").value(hasItem(DEFAULT_REQUEST_NO.intValue())));
    }
    
    @Test
    @Transactional
    public void getSlotReservationDetails() throws Exception {
        // Initialize the database
        slotReservationDetailsRepository.saveAndFlush(slotReservationDetails);

        // Get the slotReservationDetails
        restSlotReservationDetailsMockMvc.perform(get("/api/slot-reservation-details/{id}", slotReservationDetails.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(slotReservationDetails.getId().intValue()))
            .andExpect(jsonPath("$.applicantId").value(DEFAULT_APPLICANT_ID.intValue()))
            .andExpect(jsonPath("$.status").value(DEFAULT_STATUS.intValue()))
            .andExpect(jsonPath("$.timeFrom").value(DEFAULT_TIME_FROM.toString()))
            .andExpect(jsonPath("$.timeTo").value(DEFAULT_TIME_TO.toString()))
            .andExpect(jsonPath("$.requestNo").value(DEFAULT_REQUEST_NO.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSlotReservationDetails() throws Exception {
        // Get the slotReservationDetails
        restSlotReservationDetailsMockMvc.perform(get("/api/slot-reservation-details/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSlotReservationDetails() throws Exception {
        // Initialize the database
        slotReservationDetailsRepository.saveAndFlush(slotReservationDetails);

        int databaseSizeBeforeUpdate = slotReservationDetailsRepository.findAll().size();

        // Update the slotReservationDetails
        SlotReservationDetails updatedSlotReservationDetails = slotReservationDetailsRepository.findById(slotReservationDetails.getId()).get();
        // Disconnect from session so that the updates on updatedSlotReservationDetails are not directly saved in db
        em.detach(updatedSlotReservationDetails);
        updatedSlotReservationDetails
            .applicantId(UPDATED_APPLICANT_ID)
            .status(UPDATED_STATUS)
            .timeFrom(UPDATED_TIME_FROM)
            .timeTo(UPDATED_TIME_TO)
            .requestNo(UPDATED_REQUEST_NO);

        restSlotReservationDetailsMockMvc.perform(put("/api/slot-reservation-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSlotReservationDetails)))
            .andExpect(status().isOk());

        // Validate the SlotReservationDetails in the database
        List<SlotReservationDetails> slotReservationDetailsList = slotReservationDetailsRepository.findAll();
        assertThat(slotReservationDetailsList).hasSize(databaseSizeBeforeUpdate);
        SlotReservationDetails testSlotReservationDetails = slotReservationDetailsList.get(slotReservationDetailsList.size() - 1);
        assertThat(testSlotReservationDetails.getApplicantId()).isEqualTo(UPDATED_APPLICANT_ID);
        assertThat(testSlotReservationDetails.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testSlotReservationDetails.getTimeFrom()).isEqualTo(UPDATED_TIME_FROM);
        assertThat(testSlotReservationDetails.getTimeTo()).isEqualTo(UPDATED_TIME_TO);
        assertThat(testSlotReservationDetails.getRequestNo()).isEqualTo(UPDATED_REQUEST_NO);
    }

    @Test
    @Transactional
    public void updateNonExistingSlotReservationDetails() throws Exception {
        int databaseSizeBeforeUpdate = slotReservationDetailsRepository.findAll().size();

        // Create the SlotReservationDetails

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSlotReservationDetailsMockMvc.perform(put("/api/slot-reservation-details")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotReservationDetails)))
            .andExpect(status().isBadRequest());

        // Validate the SlotReservationDetails in the database
        List<SlotReservationDetails> slotReservationDetailsList = slotReservationDetailsRepository.findAll();
        assertThat(slotReservationDetailsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSlotReservationDetails() throws Exception {
        // Initialize the database
        slotReservationDetailsRepository.saveAndFlush(slotReservationDetails);

        int databaseSizeBeforeDelete = slotReservationDetailsRepository.findAll().size();

        // Delete the slotReservationDetails
        restSlotReservationDetailsMockMvc.perform(delete("/api/slot-reservation-details/{id}", slotReservationDetails.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SlotReservationDetails> slotReservationDetailsList = slotReservationDetailsRepository.findAll();
        assertThat(slotReservationDetailsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
