package com.isoft.slot.managment.service;

import com.isoft.slot.managment.domain.SlotReservationDetails;
import com.isoft.slot.managment.repository.SlotReservationDetailsRepository;
import com.isoft.slot.managment.service.dto.SlotReservationDetailsDTO;
import com.isoft.slot.managment.service.mapper.SlotReservationDetailsMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Service Implementation for managing {@link SlotReservationDetails}.
 */
@Service
@Transactional
public class SlotReservationDetailsService {

    private final Logger log = LoggerFactory.getLogger(SlotReservationDetailsService.class);

    private final SlotReservationDetailsRepository slotReservationDetailsRepository;

    private final SlotReservationDetailsMapper slotReservationDetailsMapper;

    public SlotReservationDetailsService(SlotReservationDetailsRepository slotReservationDetailsRepository, SlotReservationDetailsMapper slotReservationDetailsMapper) {
        this.slotReservationDetailsRepository = slotReservationDetailsRepository;
        this.slotReservationDetailsMapper = slotReservationDetailsMapper;
    }

    /**
     * Save a slotReservationDetails.
     *
     * @param slotReservationDetailsDTO the entity to save.
     * @return the persisted entity.
     */
    public SlotReservationDetailsDTO save(SlotReservationDetailsDTO slotReservationDetailsDTO) {
        log.debug("Request to save SlotReservationDetails : {}", slotReservationDetailsDTO);
        SlotReservationDetails slotReservationDetails = slotReservationDetailsMapper.toEntity(slotReservationDetailsDTO);
        slotReservationDetails = slotReservationDetailsRepository.save(slotReservationDetails);
        return slotReservationDetailsMapper.toDto(slotReservationDetails);
    }

    /**
     * Get all the slotReservationDetails.
     *
     * @return the list of entities.
     */
    @Transactional(readOnly = true)
    public List<SlotReservationDetailsDTO> findAll() {
        log.debug("Request to get all SlotReservationDetails");
        return slotReservationDetailsRepository.findAll().stream()
            .map(slotReservationDetailsMapper::toDto)
            .collect(Collectors.toCollection(LinkedList::new));
    }

    /**
     * Get one slotReservationDetails by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Transactional(readOnly = true)
    public Optional<SlotReservationDetailsDTO> findOne(Long id) {
        log.debug("Request to get SlotReservationDetails : {}", id);
        return slotReservationDetailsRepository.findById(id)
            .map(slotReservationDetailsMapper::toDto);
    }

    /**
     * Delete the slotReservationDetails by id.
     *
     * @param id the id of the entity.
     */
    public void delete(Long id) {
        log.debug("Request to delete SlotReservationDetails : {}", id);
        slotReservationDetailsRepository.deleteById(id);
    }

    public Optional<SlotReservationDetailsDTO> findByIdAndApplicantId(Long id, BigDecimal applicantId){
        log.debug("Request to get SlotReservationDetails : {}", id);
        return slotReservationDetailsRepository.findByIdAndApplicantId(id, applicantId)
            .map(slotReservationDetailsMapper::toDto);
    }
}
