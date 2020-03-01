package com.isoft.slot.managment.web.rest;

import com.isoft.slot.managment.SlotManagementApp;
import com.isoft.slot.managment.config.SecurityBeanOverrideConfiguration;
import com.isoft.slot.managment.domain.SlotTemplateFacilitators;
import com.isoft.slot.managment.repository.SlotTemplateFacilitatorsRepository;
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
 * Integration tests for the {@link SlotTemplateFacilitatorsResource} REST controller.
 */
@SpringBootTest(classes = {SecurityBeanOverrideConfiguration.class, SlotManagementApp.class})
public class SlotTemplateFacilitatorsResourceIT {

    private static final BigDecimal DEFAULT_COUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_COUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TYPE = new BigDecimal(1);
    private static final BigDecimal UPDATED_TYPE = new BigDecimal(2);

    @Autowired
    private SlotTemplateFacilitatorsRepository slotTemplateFacilitatorsRepository;

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

    private MockMvc restSlotTemplateFacilitatorsMockMvc;

    private SlotTemplateFacilitators slotTemplateFacilitators;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        final SlotTemplateFacilitatorsResource slotTemplateFacilitatorsResource = new SlotTemplateFacilitatorsResource(slotTemplateFacilitatorsRepository);
        this.restSlotTemplateFacilitatorsMockMvc = MockMvcBuilders.standaloneSetup(slotTemplateFacilitatorsResource)
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
    public static SlotTemplateFacilitators createEntity(EntityManager em) {
        SlotTemplateFacilitators slotTemplateFacilitators = new SlotTemplateFacilitators()
            .count(DEFAULT_COUNT)
            .type(DEFAULT_TYPE);
        return slotTemplateFacilitators;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static SlotTemplateFacilitators createUpdatedEntity(EntityManager em) {
        SlotTemplateFacilitators slotTemplateFacilitators = new SlotTemplateFacilitators()
            .count(UPDATED_COUNT)
            .type(UPDATED_TYPE);
        return slotTemplateFacilitators;
    }

    @BeforeEach
    public void initTest() {
        slotTemplateFacilitators = createEntity(em);
    }

    @Test
    @Transactional
    public void createSlotTemplateFacilitators() throws Exception {
        int databaseSizeBeforeCreate = slotTemplateFacilitatorsRepository.findAll().size();

        // Create the SlotTemplateFacilitators
        restSlotTemplateFacilitatorsMockMvc.perform(post("/api/slot-template-facilitators")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplateFacilitators)))
            .andExpect(status().isCreated());

        // Validate the SlotTemplateFacilitators in the database
        List<SlotTemplateFacilitators> slotTemplateFacilitatorsList = slotTemplateFacilitatorsRepository.findAll();
        assertThat(slotTemplateFacilitatorsList).hasSize(databaseSizeBeforeCreate + 1);
        SlotTemplateFacilitators testSlotTemplateFacilitators = slotTemplateFacilitatorsList.get(slotTemplateFacilitatorsList.size() - 1);
        assertThat(testSlotTemplateFacilitators.getCount()).isEqualTo(DEFAULT_COUNT);
        assertThat(testSlotTemplateFacilitators.getType()).isEqualTo(DEFAULT_TYPE);
    }

    @Test
    @Transactional
    public void createSlotTemplateFacilitatorsWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = slotTemplateFacilitatorsRepository.findAll().size();

        // Create the SlotTemplateFacilitators with an existing ID
        slotTemplateFacilitators.setId(1L);

        // An entity with an existing ID cannot be created, so this API call must fail
        restSlotTemplateFacilitatorsMockMvc.perform(post("/api/slot-template-facilitators")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplateFacilitators)))
            .andExpect(status().isBadRequest());

        // Validate the SlotTemplateFacilitators in the database
        List<SlotTemplateFacilitators> slotTemplateFacilitatorsList = slotTemplateFacilitatorsRepository.findAll();
        assertThat(slotTemplateFacilitatorsList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllSlotTemplateFacilitators() throws Exception {
        // Initialize the database
        slotTemplateFacilitatorsRepository.saveAndFlush(slotTemplateFacilitators);

        // Get all the slotTemplateFacilitatorsList
        restSlotTemplateFacilitatorsMockMvc.perform(get("/api/slot-template-facilitators?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(slotTemplateFacilitators.getId().intValue())))
            .andExpect(jsonPath("$.[*].count").value(hasItem(DEFAULT_COUNT.intValue())))
            .andExpect(jsonPath("$.[*].type").value(hasItem(DEFAULT_TYPE.intValue())));
    }
    
    @Test
    @Transactional
    public void getSlotTemplateFacilitators() throws Exception {
        // Initialize the database
        slotTemplateFacilitatorsRepository.saveAndFlush(slotTemplateFacilitators);

        // Get the slotTemplateFacilitators
        restSlotTemplateFacilitatorsMockMvc.perform(get("/api/slot-template-facilitators/{id}", slotTemplateFacilitators.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(slotTemplateFacilitators.getId().intValue()))
            .andExpect(jsonPath("$.count").value(DEFAULT_COUNT.intValue()))
            .andExpect(jsonPath("$.type").value(DEFAULT_TYPE.intValue()));
    }

    @Test
    @Transactional
    public void getNonExistingSlotTemplateFacilitators() throws Exception {
        // Get the slotTemplateFacilitators
        restSlotTemplateFacilitatorsMockMvc.perform(get("/api/slot-template-facilitators/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateSlotTemplateFacilitators() throws Exception {
        // Initialize the database
        slotTemplateFacilitatorsRepository.saveAndFlush(slotTemplateFacilitators);

        int databaseSizeBeforeUpdate = slotTemplateFacilitatorsRepository.findAll().size();

        // Update the slotTemplateFacilitators
        SlotTemplateFacilitators updatedSlotTemplateFacilitators = slotTemplateFacilitatorsRepository.findById(slotTemplateFacilitators.getId()).get();
        // Disconnect from session so that the updates on updatedSlotTemplateFacilitators are not directly saved in db
        em.detach(updatedSlotTemplateFacilitators);
        updatedSlotTemplateFacilitators
            .count(UPDATED_COUNT)
            .type(UPDATED_TYPE);

        restSlotTemplateFacilitatorsMockMvc.perform(put("/api/slot-template-facilitators")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(updatedSlotTemplateFacilitators)))
            .andExpect(status().isOk());

        // Validate the SlotTemplateFacilitators in the database
        List<SlotTemplateFacilitators> slotTemplateFacilitatorsList = slotTemplateFacilitatorsRepository.findAll();
        assertThat(slotTemplateFacilitatorsList).hasSize(databaseSizeBeforeUpdate);
        SlotTemplateFacilitators testSlotTemplateFacilitators = slotTemplateFacilitatorsList.get(slotTemplateFacilitatorsList.size() - 1);
        assertThat(testSlotTemplateFacilitators.getCount()).isEqualTo(UPDATED_COUNT);
        assertThat(testSlotTemplateFacilitators.getType()).isEqualTo(UPDATED_TYPE);
    }

    @Test
    @Transactional
    public void updateNonExistingSlotTemplateFacilitators() throws Exception {
        int databaseSizeBeforeUpdate = slotTemplateFacilitatorsRepository.findAll().size();

        // Create the SlotTemplateFacilitators

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restSlotTemplateFacilitatorsMockMvc.perform(put("/api/slot-template-facilitators")
            .contentType(TestUtil.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(slotTemplateFacilitators)))
            .andExpect(status().isBadRequest());

        // Validate the SlotTemplateFacilitators in the database
        List<SlotTemplateFacilitators> slotTemplateFacilitatorsList = slotTemplateFacilitatorsRepository.findAll();
        assertThat(slotTemplateFacilitatorsList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteSlotTemplateFacilitators() throws Exception {
        // Initialize the database
        slotTemplateFacilitatorsRepository.saveAndFlush(slotTemplateFacilitators);

        int databaseSizeBeforeDelete = slotTemplateFacilitatorsRepository.findAll().size();

        // Delete the slotTemplateFacilitators
        restSlotTemplateFacilitatorsMockMvc.perform(delete("/api/slot-template-facilitators/{id}", slotTemplateFacilitators.getId())
            .accept(TestUtil.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<SlotTemplateFacilitators> slotTemplateFacilitatorsList = slotTemplateFacilitatorsRepository.findAll();
        assertThat(slotTemplateFacilitatorsList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
