package com.isoft.slot.managment.service;

import com.isoft.slot.managment.domain.SlotTemplate;
import com.isoft.slot.managment.repository.SlotTemplateRepository;
import com.isoft.slot.managment.service.dto.SlotTemplateDTO;
import com.isoft.slot.managment.service.mapper.SlotTemplateMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SlotTemplate}.
 */
@Service
@Transactional
public class SlotTemplateService {

    private final Logger log = LoggerFactory.getLogger(SlotTemplateService.class);

    private final SlotTemplateRepository slotTemplateRepository;

    private final SlotTemplateMapper slotTemplateMapper;

    public SlotTemplateService(SlotTemplateRepository slotTemplateRepository, SlotTemplateMapper slotTemplateMapper) {
        this.slotTemplateRepository = slotTemplateRepository;
        this.slotTemplateMapper = slotTemplateMapper;
    }

    /**
     * Save a slotTemplate.
     *
     * @param slotTemplateDTO the entity to save.
     * @return the persisted entity.
     */
    public SlotTemplateDTO save(SlotTemplateDTO slotTemplateDTO) {
        log.debug("Request to save SlotTemplate : {}", slotTemplateDTO);
        SlotTemplate slotTemplate = slotTemplateMapper.toEntity(slotTemplateDTO);
        slotTemplate = slotTemplateRepository.save(slotTemplate);
        return slotTemplateMapper.toDto(slotTemplate);
    }

    /**
     * Get all the slotTemplates.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SlotTemplateDTO> findAll() {
        log.debug("Request to get all SlotTemplates");
        return slotTemplateRepository.findAll().stream()
            .map(slotTemplateMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one slotTemplate by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SlotTemplateDTO> findOne(Long id) {
        log.debug("Request to get SlotTemplate : {}", id);
        return slotTemplateRepository.findById(id)
            .map(slotTemplateMapper::toDto);
    }

    /**
     * Delete the slotTemplate by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SlotTemplate : {}", id);
        slotTemplateRepository.deleteById(id);
    }
}
