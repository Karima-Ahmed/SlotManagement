package com.isoft.slot.managment.service;

import com.isoft.slot.managment.domain.SlotAssets;
import com.isoft.slot.managment.repository.SlotAssetsRepository;
import com.isoft.slot.managment.service.dto.SlotAssetsDTO;
import com.isoft.slot.managment.service.mapper.SlotAssetsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SlotAssets}.
 */
@Service
@Transactional
public class SlotAssetsService {

    private final Logger log = LoggerFactory.getLogger(SlotAssetsService.class);

    private final SlotAssetsRepository slotAssetsRepository;

    private final SlotAssetsMapper slotAssetsMapper;

    public SlotAssetsService(SlotAssetsRepository slotAssetsRepository, SlotAssetsMapper slotAssetsMapper) {
        this.slotAssetsRepository = slotAssetsRepository;
        this.slotAssetsMapper = slotAssetsMapper;
    }

    /**
     * Save a slotAssets.
     *
     * @param slotAssetsDTO the entity to save.
     * @return the persisted entity.
     */
    public SlotAssetsDTO save(SlotAssetsDTO slotAssetsDTO) {
        log.debug("Request to save SlotAssets : {}", slotAssetsDTO);
        SlotAssets slotAssets = slotAssetsMapper.toEntity(slotAssetsDTO);
        slotAssets = slotAssetsRepository.save(slotAssets);
        return slotAssetsMapper.toDto(slotAssets);
    }

    /**
     * Get all the slotAssets.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SlotAssetsDTO> findAll() {
        log.debug("Request to get all SlotAssets");
        return slotAssetsRepository.findAll().stream()
            .map(slotAssetsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one slotAssets by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SlotAssetsDTO> findOne(Long id) {
        log.debug("Request to get SlotAssets : {}", id);
        return slotAssetsRepository.findById(id)
            .map(slotAssetsMapper::toDto);
    }

    /**
     * Delete the slotAssets by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SlotAssets : {}", id);
        slotAssetsRepository.deleteById(id);
    }
}
