package com.zap_dashboard.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zap_dashboard.entity.kmldistributorEntity;

public interface kmldistributorRepository extends JpaRepository<kmldistributorEntity,Long> {

	@Query("SELECT s.distributorSectorId FROM kmldistributorEntity s")
	List<String> findAllSectorIds();
	
	 @Query("SELECT z.distributorCoordinates FROM kmldistributorEntity z WHERE z.distributorSectorId = :sectorId") 
	Optional<String> findCoordinatesBydisSectorId(String sectorId);

	 @Query("SELECT k FROM kmldistributorEntity k WHERE k.distributorSectorId = :sectorId")
	List<kmldistributorEntity> findBySectorId(String sectorId);


}
