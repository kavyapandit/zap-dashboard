package com.zap_dashboard.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zap_dashboard.entity.kmltlEntity;

public interface kmltlRepository extends JpaRepository<kmltlEntity,Long> {

	@Query("SELECT s.tlSectorid FROM kmltlEntity s")
	List<String> findAllSectorIds();

	@Query("SELECT z.tlCoordinates FROM kmltlEntity z WHERE z.tlSectorid = :sectorId"
	  ) Optional<String> findCoordinatesBytlSectorId(String sectorId);

	 @Query("SELECT k FROM kmltlEntity k WHERE k.tlSectorid = :sectorId")
	List<kmltlEntity> findBySectorId(String sectorId);
	 
}
