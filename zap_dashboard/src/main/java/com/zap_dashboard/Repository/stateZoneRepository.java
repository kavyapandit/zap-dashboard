package com.zap_dashboard.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.Optional;
import com.zap_dashboard.entity.stateZoneEntity;

@Repository
public interface stateZoneRepository extends JpaRepository<stateZoneEntity, String> {

    @Query("SELECT sz.zone FROM stateZoneEntity sz WHERE sz.stateName = :state")
    Optional<String> findZoneByStateName(String state);
}
