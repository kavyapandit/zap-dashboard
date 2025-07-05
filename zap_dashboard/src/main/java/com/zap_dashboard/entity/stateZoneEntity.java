package com.zap_dashboard.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="state_zone")
public class stateZoneEntity {

	    @Id
	    private String stateName;
	    private String zone;

	    // Getters and setters
	    public String getStateName() {
	        return stateName;
	    }

	    public void setStateName(String stateName) {
	        this.stateName = stateName;
	    }

	    public String getZone() {
	        return zone;
	    }

	    public void setZone(String zone) {
	        this.zone = zone;
	    }
	}

