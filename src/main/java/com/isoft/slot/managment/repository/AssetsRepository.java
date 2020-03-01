package com.isoft.slot.managment.repository;

import com.isoft.slot.managment.domain.Assets;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Assets entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AssetsRepository extends JpaRepository<Assets, Long> {

}
