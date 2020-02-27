package com.isoft.slot.managment.repository;

import com.isoft.slot.managment.domain.SlotInstance;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SlotInstance entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SlotInstanceRepository extends JpaRepository<SlotInstance, Long> {

}
