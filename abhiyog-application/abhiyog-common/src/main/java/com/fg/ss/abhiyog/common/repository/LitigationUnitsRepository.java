package com.fg.ss.abhiyog.common.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fg.ss.abhiyog.common.model.LitigationUnits;

@Repository
public interface LitigationUnitsRepository extends JpaRepository<LitigationUnits, Integer>{

}
