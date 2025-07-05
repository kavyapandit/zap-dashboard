package com.zap_dashboard.Repository;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zap_dashboard.entity.kmlasmEntity;

public interface kmlasmRepository extends JpaRepository<kmlasmEntity, Long> {

	@Query("SELECT s.asmSectorId FROM kmlasmEntity s")
	List<String> findAllSectorIds();
	
	
	@Query("SELECT z.asmCoordinates FROM kmlasmEntity z WHERE z.asmSectorId = :sectorId")
	Optional<String> findCoordinatesByasmSectorId(String sectorId);

	 @Query("SELECT k FROM kmlasmEntity k WHERE k.asmSectorId = :sectorId")
	List<kmlasmEntity> findBySectorId(String sectorId);
	
	}
