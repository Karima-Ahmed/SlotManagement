package com.isoft.slot.managment.repository;

import com.isoft.slot.managment.domain.SlotInstance;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Spring Data  repository for the SlotInstance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SlotInstanceRepository extends JpaRepository<SlotInstance, Long> {
    @Query("SELECT slot from SlotInstance slot where slot.slotTemplate.id=:tempId and slot.timeFrom>=:timeFrom and slot.timeTo<=:timeTo and slot.centerId=:centerId and slot.availableCapacity>0")
    List<SlotInstance> getAvailableSlots(@Param("tempId") Long tempId, @Param("timeFrom") LocalDateTime timeFrom, @Param("timeTo") LocalDateTime timeTo, @Param("centerId") BigDecimal centerId );
}
