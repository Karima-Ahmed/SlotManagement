package com.isoft.slot.managment.service;

import com.isoft.slot.managment.domain.SlotFacilitators;
import com.isoft.slot.managment.repository.SlotFacilitatorsRepository;
import com.isoft.slot.managment.service.dto.SlotFacilitatorsDTO;
import com.isoft.slot.managment.service.mapper.SlotFacilitatorsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SlotFacilitators}.
 */
@Service
@Transactional
public class SlotFacilitatorsService {

    private final Logger log = LoggerFactory.getLogger(SlotFacilitatorsService.class);

    private final SlotFacilitatorsRepository slotFacilitatorsRepository;

    private final SlotFacilitatorsMapper slotFacilitatorsMapper;

    public SlotFacilitatorsService(SlotFacilitatorsRepository slotFacilitatorsRepository, SlotFacilitatorsMapper slotFacilitatorsMapper) {
        this.slotFacilitatorsRepository = slotFacilitatorsRepository;
        this.slotFacilitatorsMapper = slotFacilitatorsMapper;
    }

    /**
     * Save a slotFacilitators.
     *
     * @param slotFacilitatorsDTO the entity to save.
     * @return the persisted entity.
     */
    public SlotFacilitatorsDTO save(SlotFacilitatorsDTO slotFacilitatorsDTO) {
        log.debug("Request to save SlotFacilitators : {}", slotFacilitatorsDTO);
        SlotFacilitators slotFacilitators = slotFacilitatorsMapper.toEntity(slotFacilitatorsDTO);
        slotFacilitators = slotFacilitatorsRepository.save(slotFacilitators);
        return slotFacilitatorsMapper.toDto(slotFacilitators);
    }

    /**
     * Get all the slotFacilitators.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SlotFacilitatorsDTO> findAll() {
        log.debug("Request to get all SlotFacilitators");
        return slotFacilitatorsRepository.findAll().stream()
            .map(slotFacilitatorsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one slotFacilitators by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SlotFacilitatorsDTO> findOne(Long id) {
        log.debug("Request to get SlotFacilitators : {}", id);
        return slotFacilitatorsRepository.findById(id)
            .map(slotFacilitatorsMapper::toDto);
    }

    /**
     * Delete the slotFacilitators by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SlotFacilitators : {}", id);
        slotFacilitatorsRepository.deleteById(id);
    }
}
