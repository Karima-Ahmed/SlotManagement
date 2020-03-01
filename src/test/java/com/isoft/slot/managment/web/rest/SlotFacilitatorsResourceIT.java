package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.SlotManagementApp;
import com.isoft.slot.managment.config.SecurityBeanOverrideConfiguration;
import com.isoft.slot.managment.domain.SlotFacilitators;
import com.isoft.slot.managment.repository.SlotFacilitatorsRepository;
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
 * Integration tests for the {@link SlotFacilitatorsResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, SlotManagementApp.class})
public class SlotFacilitatorsResourceIT {

    private static final BigDecimal DEFAULT_USER_ID = new BigDecimal(1);
    private static final BigDecimal UPDATED_USER_ID = new BigDecimal(2);

    @Autowired
    private SlotFacilitatorsRepository slotFacilitatorsRepository;

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

    private MockMvc restSlotFacilitatorsMockMvc;

    private SlotFacilitators slotFacilitators;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SlotFacilitatorsResource slotFacilitatorsResource = new SlotFacilitatorsResource(slotFacilitatorsRepository);
        this.restSlotFacilitatorsMockMvc = MockMvcBuilders.standaloneSetup(slotFacilitatorsResource)
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
    public static SlotFacilitators createEntity(EntityManager em) {
        SlotFacilitators slotFacilitators = new SlotFacilitators()
            .userId(DEFAULT_USER_ID);
        return slotFacilitators;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SlotFacilitators createUpdatedEntity(EntityManager em) {
        SlotFacilitators slotFacilitators = new SlotFacilitators()
            .userId(UPDATED_USER_ID);
        return slotFacilitators;
    }

    @BeforeEach
    public void initTest() {
        slotFacilitators = createEntity(em);
    }

    @Test
    @Transactional
    public void createSlotFacilitators() throws Exception {
        int databaseSizeBeforeCreate = slotFacilitatorsRepository.findAll().size();

        // Create the SlotFacilitators
        restSlotFacilitatorsMockMvc.perform(post("/api/slot-facilitators")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotFacilitators)))
            .andExpect(status().isCreated());

        // Validate the SlotFacilitators in the database
        List<SlotFacilitators> slotFacilitatorsList = slotFacilitatorsRepository.findAll();
        assertThat(slotFacilitatorsList).hasSize(databaseSizeBeforeCreate + 1);
        SlotFacilitators testSlotFacilitators = slotFacilitatorsList.get(slotFacilitatorsList.size() - 1);
        assertThat(testSlotFacilitators.getUserId()).isEqualTo(DEFAULT_USER_ID);
    }

    @Test
    @Transactional
    public void createSlotFacilitatorsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = slotFacilitatorsRepository.findAll().size();

        // Create the SlotFacilitators with an existing ID
        slotFacilitators.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSlotFacilitatorsMockMvc.perform(post("/api/slot-facilitators")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotFacilitators)))
            .andExpect(status().isBadRequest());

        // Validate the SlotFacilitators in the database
        List<SlotFacilitators> slotFacilitatorsList = slotFacilitatorsRepository.findAll();
        assertThat(slotFacilitatorsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSlotFacilitators() throws Exception {
        // Initialize the database
        slotFacilitatorsRepository.saveAndFlush(slotFacilitators);

        // Get all the slotFacilitatorsList
        restSlotFacilitatorsMockMvc.perform(get("/api/slot-facilitators?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(slotFacilitators.getId().intValue())))
            .andExpect(jsonPath("$.[*].userId").value(hasItem(DEFAULT_USER_ID.intValue())));
    }
    
    @Test
    @Transactional
    public void getSlotFacilitators() throws Exception {
        // Initialize the database
        slotFacilitatorsRepository.saveAndFlush(slotFacilitators);

        // Get the slotFacilitators
        restSlotFacilitatorsMockMvc.perform(get("/api/slot-facilitators/{id}", slotFacilitators.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(slotFacilitators.getId().intValue()))
            .andExpect(jsonPath("$.userId").value(DEFAULT_USER_ID.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSlotFacilitators() throws Exception {
        // Get the slotFacilitators
        restSlotFacilitatorsMockMvc.perform(get("/api/slot-facilitators/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSlotFacilitators() throws Exception {
        // Initialize the database
        slotFacilitatorsRepository.saveAndFlush(slotFacilitators);

        int databaseSizeBeforeUpdate = slotFacilitatorsRepository.findAll().size();

        // Update the slotFacilitators
        SlotFacilitators updatedSlotFacilitators = slotFacilitatorsRepository.findById(slotFacilitators.getId()).get();
        // Disconnect from session so that the updates on updatedSlotFacilitators are not directly saved in db
        em.detach(updatedSlotFacilitators);
        updatedSlotFacilitators
            .userId(UPDATED_USER_ID);

        restSlotFacilitatorsMockMvc.perform(put("/api/slot-facilitators")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSlotFacilitators)))
            .andExpect(status().isOk());

        // Validate the SlotFacilitators in the database
        List<SlotFacilitators> slotFacilitatorsList = slotFacilitatorsRepository.findAll();
        assertThat(slotFacilitatorsList).hasSize(databaseSizeBeforeUpdate);
        SlotFacilitators testSlotFacilitators = slotFacilitatorsList.get(slotFacilitatorsList.size() - 1);
        assertThat(testSlotFacilitators.getUserId()).isEqualTo(UPDATED_USER_ID);
    }

    @Test
    @Transactional
    public void updateNonExistingSlotFacilitators() throws Exception {
        int databaseSizeBeforeUpdate = slotFacilitatorsRepository.findAll().size();

        // Create the SlotFacilitators

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSlotFacilitatorsMockMvc.perform(put("/api/slot-facilitators")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotFacilitators)))
            .andExpect(status().isBadRequest());

        // Validate the SlotFacilitators in the database
        List<SlotFacilitators> slotFacilitatorsList = slotFacilitatorsRepository.findAll();
        assertThat(slotFacilitatorsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSlotFacilitators() throws Exception {
        // Initialize the database
        slotFacilitatorsRepository.saveAndFlush(slotFacilitators);

        int databaseSizeBeforeDelete = slotFacilitatorsRepository.findAll().size();

        // Delete the slotFacilitators
        restSlotFacilitatorsMockMvc.perform(delete("/api/slot-facilitators/{id}", slotFacilitators.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SlotFacilitators> slotFacilitatorsList = slotFacilitatorsRepository.findAll();
        assertThat(slotFacilitatorsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
