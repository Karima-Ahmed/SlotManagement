package com.isoft.slot.managment.service;

import com.isoft.slot.managment.domain.SlotTemplateAssets;
import com.isoft.slot.managment.repository.SlotTemplateAssetsRepository;
import com.isoft.slot.managment.service.dto.SlotTemplateAssetsDTO;
import com.isoft.slot.managment.service.mapper.SlotTemplateAssetsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SlotTemplateAssets}.
 */
@Service
@Transactional
public class SlotTemplateAssetsService {

    private final Logger log = LoggerFactory.getLogger(SlotTemplateAssetsService.class);

    private final SlotTemplateAssetsRepository slotTemplateAssetsRepository;

    private final SlotTemplateAssetsMapper slotTemplateAssetsMapper;

    public SlotTemplateAssetsService(SlotTemplateAssetsRepository slotTemplateAssetsRepository, SlotTemplateAssetsMapper slotTemplateAssetsMapper) {
        this.slotTemplateAssetsRepository = slotTemplateAssetsRepository;
        this.slotTemplateAssetsMapper = slotTemplateAssetsMapper;
    }

    /**
     * Save a slotTemplateAssets.
     *
     * @param slotTemplateAssetsDTO the entity to save.
     * @return the persisted entity.
     */
    public SlotTemplateAssetsDTO save(SlotTemplateAssetsDTO slotTemplateAssetsDTO) {
        log.debug("Request to save SlotTemplateAssets : {}", slotTemplateAssetsDTO);
        SlotTemplateAssets slotTemplateAssets = slotTemplateAssetsMapper.toEntity(slotTemplateAssetsDTO);
        slotTemplateAssets = slotTemplateAssetsRepository.save(slotTemplateAssets);
        return slotTemplateAssetsMapper.toDto(slotTemplateAssets);
    }

    /**
     * Get all the slotTemplateAssets.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SlotTemplateAssetsDTO> findAll() {
        log.debug("Request to get all SlotTemplateAssets");
        return slotTemplateAssetsRepository.findAll().stream()
            .map(slotTemplateAssetsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one slotTemplateAssets by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SlotTemplateAssetsDTO> findOne(Long id) {
        log.debug("Request to get SlotTemplateAssets : {}", id);
        return slotTemplateAssetsRepository.findById(id)
            .map(slotTemplateAssetsMapper::toDto);
    }

    /**
     * Delete the slotTemplateAssets by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SlotTemplateAssets : {}", id);
        slotTemplateAssetsRepository.deleteById(id);
    }
}
