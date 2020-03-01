package com.isoft.slot.managment.repository;

import com.isoft.slot.managment.domain.SlotTemplateAssets;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the SlotTemplateAssets entity.
 */
@SuppressWarnings("unused")
@Repository
public interface SlotTemplateAssetsRepository extends JpaRepository<SlotTemplateAssets, Long> {

}
