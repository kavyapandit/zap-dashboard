package com.zap_dashboard.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zap_dashboard.entity.kmlrhEntity;

public interface kmlrhRepository extends JpaRepository<kmlrhEntity,Long>{

	@Query("SELECT s.rhSectorid FROM kmlrhEntity s")
	List<String> findAllSectorIds();

	
	  @Query("SELECT z.rhCoordinates FROM kmlrhEntity z WHERE z.rhSectorid = :sectorId")
	  Optional<String> findCoordinatesByrhSectorId(String sectorId);

	  @Query("SELECT k FROM kmlrhEntity k WHERE k.rhSectorid = :sectorId")
	  List<kmlrhEntity> findBySectorId(String sectorId);
	 

}
