package com.isoft.slot.managment.service;

import com.isoft.slot.managment.domain.SlotTemplateFacilitators;
import com.isoft.slot.managment.repository.SlotTemplateFacilitatorsRepository;
import com.isoft.slot.managment.service.dto.SlotTemplateFacilitatorsDTO;
import com.isoft.slot.managment.service.mapper.SlotTemplateFacilitatorsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SlotTemplateFacilitators}.
 */
@Service
@Transactional
public class SlotTemplateFacilitatorsService {

    private final Logger log = LoggerFactory.getLogger(SlotTemplateFacilitatorsService.class);

    private final SlotTemplateFacilitatorsRepository slotTemplateFacilitatorsRepository;

    private final SlotTemplateFacilitatorsMapper slotTemplateFacilitatorsMapper;

    public SlotTemplateFacilitatorsService(SlotTemplateFacilitatorsRepository slotTemplateFacilitatorsRepository, SlotTemplateFacilitatorsMapper slotTemplateFacilitatorsMapper) {
        this.slotTemplateFacilitatorsRepository = slotTemplateFacilitatorsRepository;
        this.slotTemplateFacilitatorsMapper = slotTemplateFacilitatorsMapper;
    }

    /**
     * Save a slotTemplateFacilitators.
     *
     * @param slotTemplateFacilitatorsDTO the entity to save.
     * @return the persisted entity.
     */
    public SlotTemplateFacilitatorsDTO save(SlotTemplateFacilitatorsDTO slotTemplateFacilitatorsDTO) {
        log.debug("Request to save SlotTemplateFacilitators : {}", slotTemplateFacilitatorsDTO);
        SlotTemplateFacilitators slotTemplateFacilitators = slotTemplateFacilitatorsMapper.toEntity(slotTemplateFacilitatorsDTO);
        slotTemplateFacilitators = slotTemplateFacilitatorsRepository.save(slotTemplateFacilitators);
        return slotTemplateFacilitatorsMapper.toDto(slotTemplateFacilitators);
    }

    /**
     * Get all the slotTemplateFacilitators.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SlotTemplateFacilitatorsDTO> findAll() {
        log.debug("Request to get all SlotTemplateFacilitators");
        return slotTemplateFacilitatorsRepository.findAll().stream()
            .map(slotTemplateFacilitatorsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one slotTemplateFacilitators by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SlotTemplateFacilitatorsDTO> findOne(Long id) {
        log.debug("Request to get SlotTemplateFacilitators : {}", id);
        return slotTemplateFacilitatorsRepository.findById(id)
            .map(slotTemplateFacilitatorsMapper::toDto);
    }

    /**
     * Delete the slotTemplateFacilitators by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SlotTemplateFacilitators : {}", id);
        slotTemplateFacilitatorsRepository.deleteById(id);
    }
}
