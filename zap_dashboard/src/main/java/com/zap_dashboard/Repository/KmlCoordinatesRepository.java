package com.zap_dashboard.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.zap_dashboard.entity.KmlCoordinates;

@Repository
public interface KmlCoordinatesRepository extends JpaRepository<KmlCoordinates, Long> {
    List<KmlCoordinates> findByStateAndDistrict(String state, String district);
    List<KmlCoordinates> findByState(String state);
    List<KmlCoordinates> findByDistrict(String district);
}

	
