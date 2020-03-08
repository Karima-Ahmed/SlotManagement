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

    List<SlotInstance> findBySlotTemplateIdAndTimeFromGreaterThanEqualAndTimeToLessThanEqualAndCenterIdAndAvailableCapacityGreaterThan(Long tempId,
                                                                                                                                      LocalDateTime timeFrom,
                                                                                                                                      LocalDateTime timeTo,
                                                                                                                                      BigDecimal centerId,
                                                                                                                                      BigDecimal availableCapacity);
}
