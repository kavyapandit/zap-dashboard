package com.zap_dashboard.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.zap_dashboard.entity.kmlzhEntity;

public interface kmlzhRepository extends JpaRepository<kmlzhEntity, Integer> {

    
    @Query("SELECT s.zhSectorid FROM kmlzhEntity s")
    List<String> findAllSectorIds();

    @Query("SELECT z.zhCoordinates FROM kmlzhEntity z WHERE z.zhSectorid = :sectorId")
    Optional<String> findCoordinatesByzhSectorId(String sectorId);

    @Query("SELECT k FROM kmlzhEntity k WHERE k.zhSectorid = :sectorId")
	List<kmlzhEntity> findBySectorId(String sectorId);
    
}
