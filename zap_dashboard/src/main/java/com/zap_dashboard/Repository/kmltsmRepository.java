package com.zap_dashboard.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zap_dashboard.entity.kmltsmEntity;

public interface kmltsmRepository extends JpaRepository<kmltsmEntity,Long>{

	@Query("SELECT s.tsmSectorid FROM kmltsmEntity s")
	List<String> findAllSectorIds();

	
	 @Query("SELECT z.tsmCoordinates FROM kmltsmEntity z WHERE z.tsmSectorid = :sectorId"
	  ) Optional<String> findCoordinatesBytsmSectorId(String sectorId);

	 @Query("SELECT k FROM kmltsmEntity k WHERE k.tsmSectorid = :sectorId")
	List<kmltsmEntity> findBySectorId(String sectorId);
	 

}
