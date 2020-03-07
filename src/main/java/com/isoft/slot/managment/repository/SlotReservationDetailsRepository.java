package com.isoft.slot.managment.repository;

import com.isoft.slot.managment.domain.SlotInstance;
import com.isoft.slot.managment.domain.SlotReservationDetails;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.Optional;

/**
 * Spring Data  repository for the SlotReservationDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SlotReservationDetailsRepository extends JpaRepository<SlotReservationDetails, Long> {
    public Optional<SlotReservationDetails> findByIdAndApplicantId(Long id, BigDecimal applicantId);
    public Optional<SlotReservationDetails> findBySlotInstanceAndApplicantId(SlotInstance slot, BigDecimal applicantId);

}
