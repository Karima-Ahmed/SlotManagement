package com.isoft.slot.managment.service;

import com.isoft.slot.managment.domain.Assets;
import com.isoft.slot.managment.repository.AssetsRepository;
import com.isoft.slot.managment.service.dto.AssetsDTO;
import com.isoft.slot.managment.service.mapper.AssetsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link Assets}.
 */
@Service
@Transactional
public class AssetsService {

    private final Logger log = LoggerFactory.getLogger(AssetsService.class);

    private final AssetsRepository assetsRepository;

    private final AssetsMapper assetsMapper;

    public AssetsService(AssetsRepository assetsRepository, AssetsMapper assetsMapper) {
        this.assetsRepository = assetsRepository;
        this.assetsMapper = assetsMapper;
    }

    /**
     * Save a assets.
     *
     * @param assetsDTO the entity to save.
     * @return the persisted entity.
     */
    public AssetsDTO save(AssetsDTO assetsDTO) {
        log.debug("Request to save Assets : {}", assetsDTO);
        Assets assets = assetsMapper.toEntity(assetsDTO);
        assets = assetsRepository.save(assets);
        return assetsMapper.toDto(assets);
    }

    /**
     * Get all the assets.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<AssetsDTO> findAll() {
        log.debug("Request to get all Assets");
        return assetsRepository.findAll().stream()
            .map(assetsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one assets by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<AssetsDTO> findOne(Long id) {
        log.debug("Request to get Assets : {}", id);
        return assetsRepository.findById(id)
            .map(assetsMapper::toDto);
    }

    /**
     * Delete the assets by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete Assets : {}", id);
        assetsRepository.deleteById(id);
    }
}
