package com.zap_dashboard.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zap_dashboard.entity.kmltseEntity;

public interface kmltseRepository extends JpaRepository<kmltseEntity,Long>{
	
	@Query("SELECT s.tseSectorid FROM kmltseEntity s")
	List<String> findAllSectorIds();

	
	  @Query("SELECT z.tseCoordinates FROM kmltseEntity z WHERE z.tseSectorid = :sectorId") 
	  Optional<String> findCoordinatesBytseSectorId(String sectorId);

	  @Query("SELECT k FROM kmltseEntity k WHERE k.tseSectorid = :sectorId")
	  List<kmltseEntity> findBySectorId(String sectorId);
	 
}
